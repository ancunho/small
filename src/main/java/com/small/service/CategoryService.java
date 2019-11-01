package com.small.service;

import com.small.common.ServerResponse;

public interface CategoryService {

    /**
     * 신규 카테고리 추가
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新分类名字
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

}
