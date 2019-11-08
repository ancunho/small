package com.small.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.service.CategoryService;
import com.small.service.ProductService;
import com.small.util.PropertiesUtil;
import com.small.vo.CATEGORY;
import com.small.vo.PRODUCT;
import com.small.vo.ProductDetailVO;
import com.small.vo.ProductListVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductServiceImpl implements ProductService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 新增产品
     *
     * @param product
     * @return
     */
    public ServerResponse saveOrUpdateProduct(PRODUCT product) {
        if (product != null) {
            //默认设置文本的第一个图片为主图片
            if (StringUtils.isNotBlank(product.getSUB_IMAGES())) {
                String[] subImageArray = product.getSUB_IMAGES().split(",");
                if (subImageArray.length > 0) {
                    product.setMAIN_IMAGE(subImageArray[0]);
                }
            }

            if (product.getID() != null) {
                //更新产品
                int resultCount = sqlSession.update("SMALL.PRODUCT.updateByPrimaryKey", product);
                if (resultCount > 0) {
                    return ServerResponse.createBySuccessMessage("更新产品成功");
                }
                return ServerResponse.createByErrorMessage("更新产品失败");
            } else {
                //新增产品
                int resultCount = sqlSession.insert("SMALL.PRODUCT.insertNewProduct", product);
                if (resultCount > 0) {
                    return ServerResponse.createBySuccessMessage("新增产品成功");
                }
                return ServerResponse.createByErrorMessage("新增产品失败");
            }

        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    /**
     * 更改销售状态
     *
     * @param productId
     * @param status
     * @return
     */
    public ServerResponse setSalesStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        PRODUCT product = new PRODUCT();
        product.setID(productId);
        product.setSTATUS(status);

        int resultCount = sqlSession.update("SMALL.PRODUCT.updateByPrimaryKeySelective", product);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("修改产品销售状态成功");
        }

        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    /**
     * 编辑产品
     *
     * @param productId
     * @return
     */
    public ServerResponse<ProductDetailVO> manageProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        PRODUCT product = sqlSession.selectOne("SMALL.PRODUCT.selectByPrimaryKey", productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }

        ProductDetailVO productDetailVO = assembleProductDetailVO(product);

        return ServerResponse.createBySuccess(productDetailVO);
    }

    /**
     * @param product
     * @return
     */
    private ProductDetailVO assembleProductDetailVO(PRODUCT product) {
        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setID(product.getID());
        productDetailVO.setCATEGORY_ID(product.getCATEGORY_ID());
        productDetailVO.setSUBTITLE(product.getSUBTITLE());
        productDetailVO.setPRICE(product.getPRICE());
        productDetailVO.setMAIN_IMAGE(product.getMAIN_IMAGE());
        productDetailVO.setSUB_IMAGES(product.getSUB_IMAGES());
        productDetailVO.setDETAIL(product.getDETAIL());
        productDetailVO.setSTOCK(product.getSTOCK());
        productDetailVO.setSTATUS(product.getSTATUS());

        //imageHost (配置文件中获取)
        productDetailVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://localhost:9200/"));

        //parent_category_id
        CATEGORY category = sqlSession.selectOne("SMALL.CATEGORY.selectByPrimaryKey", product.getCATEGORY_ID());
        if (category == null) {
            productDetailVO.setCATEGORY_ID(0);//默认根节点
        } else {
            productDetailVO.setCATEGORY_ID(category.getPARENT_ID());
        }

        productDetailVO.setCREATE_TIME(product.getCREATE_TIME());
        productDetailVO.setUPDATE_TIME(product.getUPDATE_TIME());

        return productDetailVO;
    }

    /**
     * 获取产品列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getProductList(int pageNum, int pageSize) {
        //1. startPage -- start
        PageHelper.startPage(pageNum, pageSize);
        //2. 填充自己的sql逻辑
        List<PRODUCT> productList = sqlSession.selectList("SMALL.PRODUCT.selectProductList");
        List<ProductListVO> productListVOList = Lists.newArrayList();
        for (PRODUCT productItem : productList) {
            ProductListVO productListVO = assembleProductListVO(productItem);
            productListVOList.add(productListVO);
        }
        //3. PageHelper收尾
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVOList);

        return ServerResponse.createBySuccess(pageResult);
    }

    private ProductListVO assembleProductListVO(PRODUCT product) {
        ProductListVO productListVO = new ProductListVO();
        productListVO.setID(product.getID());
        productListVO.setNAME(product.getNAME());
        productListVO.setCATEGORY_ID(product.getCATEGORY_ID());
        productListVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        productListVO.setMAIN_IMAGE(product.getMAIN_IMAGE());
        productListVO.setPRICE(product.getPRICE());
        productListVO.setSUBTITLE(product.getSUBTITLE());
        productListVO.setSTATUS(product.getSTATUS());

        return productListVO;
    }

    public ServerResponse<PageInfo> searchProduct(String productName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(productName)) {
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }

        List<PRODUCT> productList = sqlSession.selectList("SMALL.PRODUCT.selectProductListByNameKeyword", productName);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        for (PRODUCT productItem : productList) {
            ProductListVO productListVO = assembleProductListVO(productItem);
            productListVOList.add(productListVO);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVOList);
        return ServerResponse.createBySuccess(pageResult);
    }


    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
