package com.small.service.impl;

import com.small.common.ServerResponse;
import com.small.service.ProductService;
import com.small.vo.PRODUCT;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductServiceImpl implements ProductService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 新增产品
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




    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
