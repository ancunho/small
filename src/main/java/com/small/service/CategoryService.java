package com.small.service;

import com.small.common.ServerResponse;

public interface CategoryService {

    /**
     * 신규 카테고리 추가
     * @param categoryName
     * @param parentId
     * @return
     */
    public ServerResponse addCategory(String categoryName, Integer parentId);

}
