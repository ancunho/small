package com.small.service;

import com.github.pagehelper.PageInfo;
import com.small.common.ServerResponse;
import com.small.vo.PRODUCT;
import com.small.vo.ProductDetailVO;
import org.apache.ibatis.annotations.Param;

public interface ProductService {

    /**
     * 新增产品
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(PRODUCT product);


    /**
     * 修改产品状态
     * @param productId
     * @param status
     * @return
     */
    ServerResponse setSalesStatus(Integer productId, Integer status);

    /**
     * 后台获取产品信息
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVO> manageProductDetail(Integer productId);

    /**
     * 获取产品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    /**
     * 查询产品（根据产品名-模糊查询）
     * @param productName
     * @param pageNum
     * @param pageSize
     * @return
     */
//    ServerResponse<PageInfo> searchProduct(@Param() String productName, int pageNum, int pageSize);
    ServerResponse<PageInfo> searchProduct(String productName, int pageNum, int pageSize);


}
