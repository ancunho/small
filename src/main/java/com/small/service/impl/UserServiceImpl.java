package com.small.service.impl;

import com.small.common.Const;
import com.small.common.ServerResponse;
import com.small.common.TokenCache;
import com.small.controller.portal.UserController;
import com.small.service.UserService;
import com.small.util.MD5Utils;
import com.small.vo.USER;
import com.sun.xml.internal.ws.server.ServerRtException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserServiceImpl implements UserService {

    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登陆
     *
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
        logger.info(">>>>>", uservo.toString());
        return ServerResponse.createBySuccess("登录成功", uservo);
    }

    /***
     * 会员注册
     * @param user
     * @return
     */
    public ServerResponse<String> register(USER user) {
        ServerResponse validResponse = this.checkValid(user.getUSERNAME(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getEMAIL(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setROLE(Const.Role.ROLE_CUSTOMER);
        user.setPASSWORD(MD5Utils.MD5EncodeUtf8(user.getPASSWORD()));
        int resultCount = sqlSession.insert("SMALL.USER.register", user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 校验用户名以及邮箱
     *
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String str, String type) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
            //开始校验
            //用户名校验
            if (Const.USERNAME.equals(type)) {
                int resultCount = sqlSession.selectOne("SMALL.USER.checkUsername", str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            //邮箱校验
            if (Const.EMAIL.equals(type)) {
                int resultCount = sqlSession.selectOne("SMALL.USER.checkEmail", str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("Email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 通过用户名找回他的问题
     * @param username
     * @return
     */
    public ServerResponse selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String question = sqlSession.selectOne("SMALL.USER.selectQuestionByUsername", username);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");

    }

    /**
     * 验证问题的答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        Map<String, String> param = new HashMap<>();
        param.put("USERNAME", username);
        param.put("QUESTION", question);
        param.put("ANSWER", answer);
        int resultCount = sqlSession.selectOne("SMALL.USER.checkAnswer", param);
        if (resultCount > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误！");
    }


    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken) {
        if (org.apache.commons.lang3.StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if(org.apache.commons.lang3.StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }

        if (org.apache.commons.lang3.StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Utils.MD5EncodeUtf8(passwordNew);
            //todo

        }
        //todo
        return null;
    }


    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


}
