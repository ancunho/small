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
    ServerResponse selectQuestion(String username);

    /**
     * 验证问题的答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);
}
