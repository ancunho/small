package com.small.service.impl;

import com.small.common.ServerResponse;
import com.small.service.CategoryService;
import com.small.vo.CATEGORY;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
        logger.info("category>>>>>>>>" + category.toString());
        int resultCount = sqlSession.insert("SMALL.CATEGORY.addCategory", category);
        logger.info("resultCount>>>>>>>>" + resultCount);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加分类失败");
        }
        return ServerResponse.createBySuccessMessage("添加分类成功");
    }


    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
