package com.blade.archetype.controller.errorpage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/4/29 12:16
 */
@RestController("/error")
public class ErrorController {

    @RequestMapping(value = "/404.html")
    public String pageNotFound() {
        return "404";
    }
}
