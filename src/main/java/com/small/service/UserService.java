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
}
