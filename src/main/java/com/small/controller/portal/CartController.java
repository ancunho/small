package com.small.controller.portal;

import com.small.common.Const;
import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.service.CartService;
import com.small.vo.CartItemVO;
import com.small.vo.USER;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartService cartService;

    /**
     * 장바구니에 담기.  전에 없으면 신규추가. 있으면 업데이트
     * @param session
     * @param count
     * @param productId
     * @return
     */
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse<CartItemVO> add(HttpSession session, Integer count, Integer productId) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return cartService.add(user.getID(), productId, count);
    }

    /**
     * 更新购物车数量
     * @param session
     * @param count
     * @param productId
     * @return
     */
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartItemVO> update(HttpSession session, Integer count, Integer productId) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return cartService.update(user.getID(), productId, count);
    }

    @RequestMapping(value = "delete_product.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartItemVO> deleteProduct(HttpSession session, String productIds) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return cartService.deleteProduct(user.getID(), productIds);
    }

    @RequestMapping(value = "select_all.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartItemVO> selectAll(HttpSession session) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        return cartService.selectOrUnSelect(user.getID(), null,Const.Cart.CHECKED);
    }

    @RequestMapping(value = "un_select_all.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartItemVO> unSelectAll(HttpSession session) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        return cartService.selectOrUnSelect(user.getID(), null,Const.Cart.UN_CHECKED);
    }

    @RequestMapping(value = "select.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartItemVO> select(HttpSession session, Integer productId) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        return cartService.selectOrUnSelect(user.getID(), productId,Const.Cart.CHECKED);
    }

    @RequestMapping(value = "un_select.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartItemVO> unSelect(HttpSession session, Integer productId) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        return cartService.selectOrUnSelect(user.getID(), productId,Const.Cart.UN_CHECKED);
    }


    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
