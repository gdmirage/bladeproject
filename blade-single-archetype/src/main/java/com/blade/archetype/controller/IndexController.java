package com.blade.archetype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/4/29 14:23
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }
}
