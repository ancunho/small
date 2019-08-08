package com.small.service;

import com.small.common.ServerResponse;
import com.small.vo.USER;

public interface UserService {

    public ServerResponse<USER> login(String username, String password);


}
