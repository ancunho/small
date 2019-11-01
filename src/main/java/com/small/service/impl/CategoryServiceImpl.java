package com.small.service.impl;

import com.small.common.ServerResponse;
import com.small.service.CategoryService;
import com.small.vo.CATEGORY;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    public ServerResponse<List<CATEGORY>> getChildrenParallelCategory(Integer categoryId) {


        return null;
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
