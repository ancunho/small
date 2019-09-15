package com.small.controller.portal;

import com.small.common.Const;
import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.service.UserService;
import com.small.service.impl.UserServiceImpl;
import com.small.vo.USER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<USER> login(String username, String password, HttpSession session) {
        logger.info("User Login: username:" + username + ", | password:" + password);
        ServerResponse<USER> response = userService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout (HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(USER user) {
        return userService.register(user);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<USER> getUserInfo(HttpSession session) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登陆， 无法获取当前用户的信息");
    }

    /**
     * 找回密码问题-》 通过用户名
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return userService.selectQuestion(username);
    }

    /**
     * 验证问题的答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return userService.checkAnswer(username,question, answer);
    }

    /**
     * 忘记密码步骤的密码修改
     * @param username
     * @param passwordnew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username, String passwordnew, String forgetToken) {
        return userService.forgetResetPassword(username, passwordnew, forgetToken);
    }

    /**
     * 登陆状态下更新密码
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }

    /**
     * 更新个人信息
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<USER> updateInformation(HttpSession session, USER user) {
        USER currentUser = (USER) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setID(currentUser.getID());
        user.setUSERNAME(currentUser.getUSERNAME());
        ServerResponse<USER> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 获取个人信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<USER> getInformation(HttpSession session) {
        USER currentUser = (USER) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录， 需要强制登陆status=10");
        }

        return userService.getInformation(currentUser.getID());
    }



    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
