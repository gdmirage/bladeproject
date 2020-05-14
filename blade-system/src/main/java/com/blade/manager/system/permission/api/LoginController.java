package com.blade.manager.system.permission.api;

import com.blade.manager.system.common.CommonController;
import com.blade.manager.system.permission.model.login.ImgResult;
import com.blade.manager.system.permission.model.login.LoginDTO;
import com.blade.manager.system.permission.model.login.LoginVO;
import com.blade.manager.system.permission.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 登陆控制器
 *
 * @author blade
 * 2019/12/20 17:21
 */
@RestController("ApiLoginController")
@RequestMapping("/api/permission/login")
public class LoginController extends CommonController {

    private ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/getCaptcha")
    public ImgResult getCaptcha() throws Exception {
        return this.loginService.getCaptcha();
    }

    @PostMapping("/login")
    public LoginVO login(@RequestBody LoginDTO loginDTO) throws Exception {
        return loginService.login(loginDTO);
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletRequest request) throws Exception {
        loginService.logout(super.getToken(request));
    }
}
