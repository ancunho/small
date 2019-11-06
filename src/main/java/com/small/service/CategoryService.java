package com.small.service;

import com.small.common.ServerResponse;
import com.small.vo.CATEGORY;

import java.util.List;

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

    /**
     *
     * @param categoryId
     * @return
     */
    ServerResponse<List<CATEGORY>> getChildrenParallelCategory(Integer categoryId);


    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
