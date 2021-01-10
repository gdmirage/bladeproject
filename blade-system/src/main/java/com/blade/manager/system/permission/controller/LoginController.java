package com.blade.manager.system.permission.controller;

import com.blade.manager.system.permission.model.login.ImgResult;
import com.blade.manager.system.permission.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 登陆控制器
 *
 * @author blade
 * 2019/12/20 17:21
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/getCaptcha")
    @ResponseBody
    public ImgResult getCaptcha() throws IOException {
        return this.loginService.getCaptcha();
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/index");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/login");
        return mv;
    }
}
