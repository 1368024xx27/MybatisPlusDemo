package com.vrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转
 *
 * @author zhu
 */
@Controller
public class PageManagerController {

    // 跳到主页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    // 显示其他页面
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String shopPage(@PathVariable(value = "page") String page) {
        return page;
    }
}
