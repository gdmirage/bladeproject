package com.blade.manager.system.common;

import com.blade.core.controller.BaseController;
import com.blade.manager.system.permission.model.login.LoginUser;
import com.blade.starter.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO:
 * 公共controller
 *
 * @author Blade
 * @date 2020/1/4 14:55
 */
@Component
public class CommonController extends BaseController {
    private static final long serialVersionUID = 5597058920650602312L;

    @Autowired
    protected RedisUtils redisUtils;

    /**
     * 获取登陆用户对象
     *
     * @param request http request
     * @return {@link LoginUser}
     */
    protected LoginUser getLoginUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        super.logger.info("token is {}", token);

        Object o = this.redisUtils.get(token);

        return (LoginUser) o;
    }

    /**
     * 获取登陆用户ID
     *
     * @param request http request
     * @return login user id
     */
    protected Long getLoginUserId(HttpServletRequest request) {
        return this.getLoginUser(request).getUserId();
    }
}
