package com.small.controller.page;

import com.small.common.Const;
import com.small.common.ServerResponse;
import com.small.vo.USER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class PortalController {

    @RequestMapping(value = "login.do")
    public String login(HttpSession session) {
//        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
//        if (user != null) {
//
//        }
        return "login";

    }




}
