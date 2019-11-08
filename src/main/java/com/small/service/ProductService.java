package com.small.service;

import com.small.common.ServerResponse;
import com.small.vo.PRODUCT;

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

}
