package com.aircjm.limon.project.system.controller;

import java.util.List;
import java.util.Set;

import com.aircjm.limon.vo.RestReult;
import com.aircjm.limon.security.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.aircjm.limon.utils.ServletUtils;
import com.aircjm.limon.security.LoginBody;
import com.aircjm.limon.security.LoginUser;
import com.aircjm.limon.security.service.SysLoginService;
import com.aircjm.limon.security.service.SysPermissionService;
import com.aircjm.limon.security.service.TokenService;
import com.aircjm.limon.web.domain.AjaxResult;
import com.aircjm.limon.project.system.domain.SysMenu;
import com.aircjm.limon.project.system.domain.SysUser;
import com.aircjm.limon.project.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author aircjm
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public RestReult<UserLoginResponse> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return RestReult.successData(UserLoginResponse.builder().token(token).build());
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
