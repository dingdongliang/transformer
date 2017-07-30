package com.dyenigma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * author  dyenigma
 * date 2017/7/21 15:51
 */
@Controller
@RequestMapping(value = "/manage")
public class MgrJumpController extends BaseController {

    @RequestMapping(value = "/mTop", method = RequestMethod.GET)
    public String mTop(HttpServletRequest request, Model model) {
        logger.debug("mTop() is executed!");
        model.addAttribute("currUser", request.getAttribute("currUser"));
        return "manage/layout/header";
    }

    @RequestMapping(value = "/mLeft", method = RequestMethod.GET)
    public String mLeft() {
        logger.debug("mLeft() is executed!");
        return "manage/layout/left";
    }

    @RequestMapping(value = "/mFoot", method = RequestMethod.GET)
    public String mFoot() {
        logger.debug("mFoot() is executed!");
        return "manage/layout/footer";
    }

    @RequestMapping(value = "/mCenter", method = RequestMethod.GET)
    public String mCenter() {
        logger.debug("mCenter() is executed!");
        return "manage/layout/center";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        logger.debug("来自IP[" + request.getRemoteHost() + "]的访问");
        return "manage/login";
    }

    @RequestMapping("/main")
    public String main() {
        logger.debug("main() is executed!");
        return "manage/main";
    }
}
