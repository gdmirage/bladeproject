package com.blade.manager.system.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO:
 *
 * @author blade
 * 2019/12/26 14:33
 */
@Controller
@RequestMapping("/test")
public class IndexController {

    @RequestMapping("")
    public String index() {
        System.out.println("test");
        return "index";
    }

    @RequestMapping("/mv")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("hello", "hello");
        modelAndView.addObject("world", "world");
        return modelAndView;
    }
}
