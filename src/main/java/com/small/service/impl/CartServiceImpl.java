package com.small.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.small.common.Const;
import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.service.CartService;
import com.small.util.BigDecimalUtil;
import com.small.util.PropertiesUtil;
import com.small.vo.CART;
import com.small.vo.CartItemVO;
import com.small.vo.CartProductVO;
import com.small.vo.PRODUCT;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartServiceImpl implements CartService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    /**
     * 장바구니에 담기.  전에 없으면 신규추가. 있으면 업데이트
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    public ServerResponse<CartItemVO> add(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        logger.info("cart add>>>>userId>>>>>" + userId);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("USER_ID", userId);
        param.put("PRODUCT_ID", productId);
        CART cart = sqlSession.selectOne("SMALL.CART.selectCartByUserIdProductId", param);

        if (cart == null) {
            //전에 장바구니에 넣은게 없음.. 새로 추가
            CART cartItem = new CART();
            cartItem.setQUANTITY(count);
            cartItem.setCHECKED(Const.Cart.CHECKED);
            cartItem.setPRODUCT_ID(productId);
            cartItem.setUSER_ID(userId);

            sqlSession.insert("SMALL.CART.insertCART", cartItem);
        } else {
            //전에 넣은게 있음, 개수랑 업데이트해야됨
            count = cart.getQUANTITY() + count;
            cart.setQUANTITY(count);
            sqlSession.update("SMALL.CART.updateByPrimaryKeySelective", cart);
        }

        return this.list(userId);
    }

    /**
     * 更新购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    public ServerResponse<CartItemVO> update(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("USER_ID", userId);
        param.put("PRODUCT_ID", productId);
        CART cart = sqlSession.selectOne("SMALL.CART.selectCartByUserIdProductId", param);
        if (cart !=  null) {
            cart.setQUANTITY(count);
        }

        sqlSession.update("SMALL.CART.updateByPrimaryKey", cart);
        return this.list(userId);
    }

    /**
     * 删除购物车里的产品
     * @param userId
     * @param productIds
     * @return
     */
    public ServerResponse<CartItemVO> deleteProduct(Integer userId, String productIds) {
        List<String> productIdList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productIdList)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("USER_ID", userId);
        param.put("productIdList", productIdList);
        sqlSession.delete("SMALL.CART.deleteByUserIdProductIds", param);

        return this.list(userId);
    }

    /**
     * 选择或取消选择（包括单选和全选--根据productId的值来判断
     * 如果productId为空值，认为全部操作
     * @param userId
     * @param productId
     * @param checked
     * @return
     */
    public ServerResponse<CartItemVO> selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("USER_ID", userId);
        param.put("PRODUCT_ID", productId);
        param.put("CHECKED", checked);
        sqlSession.update("SMALL.CART.checkedOrUncheckedProduct", param);
        return this.list(userId);
    }

    public ServerResponse<CartItemVO> list(Integer userId){
        CartItemVO cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }

    private CartItemVO getCartVoLimit(Integer userId) {
        logger.info("userId-->" + userId);
        CartItemVO cartItemVO = new CartItemVO();
        //1. 사용자의 장바구니 리스트목록을 가져온다.
        List<CART> cartList = sqlSession.selectList("SMALL.CART.selectCartByUserId", userId);
        List<CartProductVO> cartProductVOList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(cartList)) {
            //1에 따른 결과있을때. 즉 장바구니가 비여있지 않을때.
            //for문 돌려서 제품들을 하나하나 담음.
            for (CART cartItem : cartList) {
                CartProductVO cartProductVO = new CartProductVO();
                cartProductVO.setID(cartItem.getID());
                cartProductVO.setUSER_ID(userId);
                cartProductVO.setPRODUCT_ID(cartItem.getPRODUCT_ID());
                //productId에 따른 제품정보가져온다.
                PRODUCT product = sqlSession.selectOne("SMALL.PRODUCT.selectByPrimaryKey", cartItem.getPRODUCT_ID());
                if (product != null) {
                    cartProductVO.setPRODUCT_MAIN_IMAGE(product.getMAIN_IMAGE());
                    cartProductVO.setPRODUCT_NAME(product.getNAME());
                    cartProductVO.setPRODUCT_SUB_TITLE(product.getSUBTITLE());
                    cartProductVO.setPRODUCT_STATUS(product.getSTATUS());
                    cartProductVO.setPRODUCT_PRICE(new BigDecimal(product.getPRICE()));
                    cartProductVO.setPRODUCT_STOCK(product.getSTOCK());
                    //判断库存
                    int buyLimitCount = 0;
                    if (product.getSTOCK() >= cartItem.getQUANTITY()) {
                        //库存充足的时候
                        buyLimitCount = cartItem.getQUANTITY();
                        cartProductVO.setLIMIT_QUANTITY(Const.Cart.LIMIT_NUM_SUCCESS);
                    } else {
                        buyLimitCount = product.getSTOCK();
                        cartProductVO.setLIMIT_QUANTITY(Const.Cart.LIMIT_NUM_FAIL);
                        //购物车中更新有效库存
                        CART cartForQuantity = new CART();
                        cartForQuantity.setID(cartItem.getID());
                        cartForQuantity.setQUANTITY(buyLimitCount);
                        sqlSession.update("SMALL.CART.updateByPrimaryKeySelective", cartForQuantity);
                    }
                    cartProductVO.setQUANTITY(buyLimitCount);
                    //计算总价
                    cartProductVO.setPRODUCT_TOTAL_PRICE(BigDecimalUtil.mul(product.getPRICE().doubleValue(), cartProductVO.getQUANTITY()));
                    cartProductVO.setPRODUCT_CHECKED(cartItem.getCHECKED());

                    if (cartItem.getCHECKED() == Const.Cart.CHECKED) {
                        //如果已经勾选,增加到整个的购物车总价中
                        logger.info("cartProductVO.getPRODUCT_TOTAL_PRICE():" + cartProductVO.getPRODUCT_TOTAL_PRICE());
                        if (cartProductVO.getPRODUCT_TOTAL_PRICE() == null) {
                            cartProductVO.setPRODUCT_TOTAL_PRICE(new BigDecimal("0"));
                        }
                        cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVO.getPRODUCT_TOTAL_PRICE().doubleValue());
                    }

                    cartProductVOList.add(cartProductVO);
                }

            }

        }
        cartItemVO.setCartTotalPrice(cartTotalPrice);
        cartItemVO.setCartProductVOList(cartProductVOList);
        cartItemVO.setAllChecked(this.getAllCheckedStatus(userId));
        cartItemVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));

        return cartItemVO;
    }

    private boolean getAllCheckedStatus(Integer userId) {
        if(userId == null){
            return false;
        }
        int resultCount = sqlSession.selectOne("SMALL.CART.selectCartProductCheckedStatusByUserId", userId);
        if (resultCount == 0) {
            return true;
        } else {
            return false;
        }
    }


    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
