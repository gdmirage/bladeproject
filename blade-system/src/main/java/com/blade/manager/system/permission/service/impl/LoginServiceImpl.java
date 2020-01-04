package com.blade.manager.system.permission.service.impl;

import com.blade.core.enums.ValidateResultCodeEnum;
import com.blade.core.exception.ServiceException;
import com.blade.core.model.base.JsonAble;
import com.blade.manager.system.constant.Constants;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.model.login.LoginDTO;
import com.blade.manager.system.permission.model.login.LoginUser;
import com.blade.manager.system.permission.model.login.LoginVO;
import com.blade.manager.system.permission.service.IDeptService;
import com.blade.manager.system.permission.service.IJobService;
import com.blade.manager.system.permission.service.ILoginService;
import com.blade.manager.system.permission.service.IMenuService;
import com.blade.manager.system.permission.service.IUserRolesService;
import com.blade.manager.system.permission.service.IUserService;
import com.blade.starter.redis.RedisUtils;
import com.blade.util.CaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TODO: 登陆服务
 *
 * @author Blade
 * @date 2020/1/2 15:41
 */
@Service("loginService")
public class LoginServiceImpl extends JsonAble implements ILoginService {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private IUserRolesService userRolesService;

    @Autowired
    private IMenuService menuService;

    @Override
    public LoginVO login(LoginDTO loginDTO) throws ServiceException {
        if (StringUtils.isBlank(loginDTO.getLoginName())) {
            throw new ServiceException(ValidateResultCodeEnum.EMPTY_ACCOUNT);
        }

        if (StringUtils.isBlank(loginDTO.getPassword())) {
            throw new ServiceException(ValidateResultCodeEnum.EMPTY_PASSWORD);
        }

        if (StringUtils.isBlank(loginDTO.getCode())) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_CAPTCHA);
        }

        String captcha = redisUtils.get(loginDTO.getUuid()).toString();
        super.logger.info("captcha {}", captcha);
        if (!loginDTO.getCode().equalsIgnoreCase(captcha)) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_CAPTCHA);
        }

        User user = this.userService.findUserByLoginNameOrEmail(loginDTO.getLoginName());

        if (null == user) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_ACCOUNT_OR_PASSWORD);
        }

        if (!Objects.equals(user.getPassword(), loginDTO.getPassword())) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_ACCOUNT_OR_PASSWORD);
        }

        super.logger.debug(user.toString());

        String token = CaptchaUtil.generateVerifyCode(64);

        LoginUser loginUser = this.getLoginUser(user);

        // 存入缓存
        redisUtils.save(token, loginUser, Constants.Cache.TOKEN_EXPIRE_TIME);

        return new LoginVO(token, loginUser);
    }

    private LoginUser getLoginUser(User user) {
        LoginUser loginUser = new LoginUser();

        loginUser.setAvatar(user.getAvatar());
        loginUser.setEmail(user.getEmail());
        loginUser.setMobilePhone(user.getMobilePhone());
        loginUser.setUsername(user.getUserName());
        loginUser.setLoginName(user.getLoginName());

        Dept dept = this.deptService.selectByPk(user.getDeptId());

        loginUser.setDeptName(Optional.ofNullable(dept).map(Dept::getDeptName).orElse(null));

        Job job = this.jobService.selectByPk(user.getJobId());
        loginUser.setUsername(Optional.ofNullable(job).map(Job::getJobName).orElse(null));

        List<Menu> menus = this.menuService.selectMenuByRoleIds(this.userRolesService.getRoleIdsByUserId(user.getId()));

        List<String> roles = menus.stream().filter(menu -> StringUtils.isNotBlank(menu.getPermissionCode()))
                .map(Menu::getPermissionCode).collect(Collectors.toList());
        loginUser.setRoles(roles);

        return loginUser;
    }
}
