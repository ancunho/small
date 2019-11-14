package com.small.service;

import com.small.common.ServerResponse;
import com.small.vo.CartItemVO;

public interface CartService {

    ServerResponse<CartItemVO> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartItemVO> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartItemVO> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartItemVO> selectOrUnSelect(Integer userId, Integer productId, Integer checked);
}
