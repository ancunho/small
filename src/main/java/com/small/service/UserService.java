package com.small.service;

import com.small.common.ServerResponse;
import com.small.vo.USER;

public interface UserService {

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    ServerResponse<USER> login(String username, String password);

    /**
     * 会员注册
     * @param user
     * @return
     */
    ServerResponse<String> register(USER user);


    /**
     * 找回密码问题-》 通过用户名
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);

    /**
     * 验证问题的答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);


    /**
     * 忘记密码步骤的密码修改
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    /**
     * 登陆状态下更新密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, USER user);

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    ServerResponse<USER> updateInformation(USER user);

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    ServerResponse<USER> getInformation(Integer userId);
}
