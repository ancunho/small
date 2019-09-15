package com.small.controller.page;

import com.small.common.Const;
import com.small.common.ServerResponse;
import com.small.vo.USER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/***
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Page URL
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
@Controller
@RequestMapping("/page/")
public class PortalController {

    @RequestMapping(value = "index.do")
    public String index(HttpSession session) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            if (user.getROLE() == 1) {
                //管理员通道
                return "index";
            } else {
                //普通员工通道
                return "index2";
            }
        }
        return "redirect:/page/login.do";
    }

    /**
     * 登陆URL
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do")
    public String login(HttpSession session) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return "redirect:/page/index.do";
        } else {
            return "login";
        }
    }

    /**
     * 登出URL
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return "redirect:/page/login.do";
    }

    /**
     * 找回密码页面
     * @return
     */
    @RequestMapping(value = "user/forget_password.do")
    public String forget_password() {
        return "/user/forget_password";
    }


}
