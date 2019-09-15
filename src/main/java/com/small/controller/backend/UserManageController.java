package com.small.controller.backend;

import com.small.common.Const;
import com.small.common.ServerResponse;
import com.small.service.UserService;
import com.small.vo.USER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/managed/user/")
public class UserManageController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "loginM.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<USER> loginM(String username, String password, HttpSession session) {
        ServerResponse<USER> response = userService.login(username,password);
        if (response.isSuccess()) {
            USER user = response.getData();
            if (user.getROLE() == Const.Role.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员， 无法登陆");
            }
        }
        return response;
    }

//    @RequestMapping(value = "get_all_user.do", method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse<USER> getAllUser(HttpSession session) {
////        ServerResponse response =
//
//        return response;
//    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
