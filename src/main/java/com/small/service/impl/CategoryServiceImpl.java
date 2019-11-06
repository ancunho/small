package com.small.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.small.common.ServerResponse;
import com.small.service.CategoryService;
import com.small.vo.CATEGORY;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CategoryServiceImpl implements CategoryService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 신규 카테고리 추가
     * @param categoryName
     * @param parentId
     * @return
     */
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        CATEGORY category = new CATEGORY();
        category.setNAME(categoryName);
        category.setPARENT_ID(parentId);
        category.setSTATUS(1);
        category.setSORT_ORDER(0);

        int resultCount = sqlSession.insert("SMALL.CATEGORY.addCategory", category);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加分类失败");
        }
        return ServerResponse.createBySuccessMessage("添加分类成功");
    }

    /**
     * 更新分类名字
     * @param categoryId
     * @param categoryName
     * @return
     */
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新分类参数错误");
        }

        CATEGORY category = new CATEGORY();
        category.setID(categoryId);
        category.setNAME(categoryName);

        logger.info("category>>>>>>>>>>>" + category.toString());

//        try {
//            sqlSession.update("SMALL.CATEGORY.updateCategoryName", category);
//            return ServerResponse.createBySuccess("更新分类名字成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ServerResponse.createByErrorMessage("更新分类名字失败");
//        }

        int resultCount = sqlSession.update("SMALL.CATEGORY.updateCategoryName", category);
        logger.info("resultCount>>>" + resultCount);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("更新分类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新分类名字失败");

    }


    /**
     *
     * @param categoryId
     * @return
     */
    public ServerResponse<List<CATEGORY>> getChildrenParallelCategory(Integer categoryId) {
        logger.info(">>>>>>>getChildrenParallelCategory categoryID:" + categoryId);
        List<CATEGORY> categoryList = sqlSession.selectList("SMALL.CATEGORY.selectCategoryChildrenByParentId", categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }

        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<CATEGORY> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (CATEGORY categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getID());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    //递归算法,算出子节点
    private Set<CATEGORY> findChildCategory(Set<CATEGORY> categorySet, Integer categoryId) {
        CATEGORY category = sqlSession.selectOne("SMALL.CATEGORY.selectByPrimaryKey", categoryId);
        if (category != null) {
            categorySet.add(category);
        }

        //查找子节点,递归算法一定要有一个退出的条件
        List<CATEGORY> categoryList = sqlSession.selectList("SMALL.CATEGORY.selectCategoryChildrenByParentId", categoryId);
        for (CATEGORY categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getID());
        }

        return categorySet;
    }


    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
