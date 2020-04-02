package com.ruoyi.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserGoogle;
import com.ruoyi.system.service.ISysUserGoogleService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;

import java.util.Calendar;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController {

    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserGoogleService sysUserGoogleService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }

    /**
     * 验证用户的登陆密码
     *
     * @param username 登陆用户名
     * @param password 登陆密码
     * @return 返回结果
     */
    @PostMapping("/bind")
    @ResponseBody
    public AjaxResult bind(String username, String password, ModelMap mmap) {
        if (StringUtils.isEmpty(username)) {
            return error("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return error("登陆密码不能为空");
        }
        SysUser sysUser = userService.selectUserByLoginName(username);
        if (sysUser == null) {
            return error("输入用户名有误");
        }
        String verify = passwordService.encryptPassword(username, password, sysUser.getSalt());
        if (!sysUser.getPassword().equals(verify)) {
            return error("输入密码有误");
        }
        SysUserGoogle sysUserGoogle = sysUserGoogleService.selectSysUserGoogleByUsername(username);
        if (sysUserGoogle == null) {
            return error("后台未绑定GOOGLE验证器，请联系管理员");
        }
        Long expire = sysUserGoogle.getExpireTime() * 60;
        Long now = System.currentTimeMillis() / 1000;
        Long past = sysUserGoogle.getCreateTime().getTime() / 1000;
        if (now - past > expire) {
            return error("二维码已过期，重新绑定请联系管理员");
        }
        mmap.put("google", sysUserGoogle.getGoogleUrl());
        return AjaxResult.success(mmap);
    }

}
