package com.small.service.impl;

import com.small.common.ServerResponse;
import com.small.controller.portal.UserController;
import com.small.service.UserService;
import com.small.util.MD5Utils;
import com.small.vo.USER;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserServiceImpl implements UserService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<USER> login(String username, String password) {
        int resultCount = sqlSession.selectOne("SMALL.USER.checkUsername", username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5password = MD5Utils.MD5EncodeUtf8(password);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("USERNAME", username);
        param.put("PASSWORD", md5password);
        USER uservo = sqlSession.selectOne("SMALL.USER.selectLogin", param);
        if (uservo == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        uservo.setPASSWORD(StringUtils.EMPTY);
        logger.info(">>>>>",uservo.toString());
        return ServerResponse.createBySuccess("登录成功", uservo);
    }



    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


}
