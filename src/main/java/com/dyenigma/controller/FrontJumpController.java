package com.dyenigma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 前台页面跳转控制
 * author  dyenigma
 * date 2017/7/21 15:36
 */
@Controller
@RequestMapping(value = "/frontJump")
public class FrontJumpController extends BaseController {

    @GetMapping(value = "/show")
    public String show(Model model) {
        Map<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("time", new Date());
        objectHashMap.put("message", "这是测试的内容。。。");
        objectHashMap.put("toUserName", "张三");
        objectHashMap.put("fromUserName", "老许");

        model.addAttribute("userList", objectHashMap);

        return "hello";


    }

    @GetMapping(value = "/base")
    public String base() {
        logger.debug("base() is executed!");
        return "main/base";
    }

    @GetMapping(value = "/main")
    public String main() {
        logger.debug("main() is executed!");
        return "main/main";
    }

    @GetMapping(value = "/adv")
    public String adv() {
        logger.debug("adv() is executed!");
        return "main/adv";
    }

    @GetMapping(value = "/web")
    public String web() {
        logger.debug("web() is executed!");
        return "main/web";
    }

    @GetMapping(value = "/sources")
    public String sources() {
        logger.debug("sources() is executed!");
        return "main/sources";
    }

    @GetMapping(value = "/database")
    public String database() {
        logger.debug("database() is executed!");
        return "main/database";
    }

    @RequestMapping(value = "/question")
    public String question() {
        logger.debug("question() is executed!");
        return "main/question";
    }

    @GetMapping(value = "/life")
    public String life() {
        logger.debug("life() is executed!");
        return "main/life";
    }

    @GetMapping(value = "/tools")
    public String tools() {
        logger.debug("tools() is executed!");
        return "main/tools";
    }
}
