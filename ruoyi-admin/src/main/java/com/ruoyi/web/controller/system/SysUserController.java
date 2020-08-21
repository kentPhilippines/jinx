package com.ruoyi.web.controller.system;

import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.GoogleUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserGoogle;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserGoogleService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    private String prefix = "system/user";
    @Autowired private ISysUserService userService;
    @Autowired private ISysRoleService roleService;
    @Autowired private ISysPostService postService;
    @Autowired private SysPasswordService passwordService;
    @Autowired private GoogleUtils googleUtils;
    @Autowired private ISysUserGoogleService sysUserGoogleService;
    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    static final String ADMIN = "admin";
    static final String ADMIN_NUMBER = "1";

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<SysRole> sysRoles = roleService.selectRoleAll();
        String operName = ShiroUtils.getSysUser().getLoginName();
        if (!ADMIN.equals(operName)) {
            List<SysRole> roleList = new ArrayList<>();
            for (SysRole role : sysRoles) {
                if (role.getRoleId() == 113) {
                    roleList.add(role);
                }
            }
            sysRoles = roleList;
        }
        mmap.put("roles", sysRoles);
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setFundPassword(passwordService.encryptPassword(user.getLoginName(), user.getFundPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user) {
//        userService.checkUserAllowed(user);
        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        userService.checkUserAllowed(user);
        SysUser user1 = userService.selectUserById(user.getUserId());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user1.getSalt()));
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId() == user.getUserId()) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    @Log(title = "修改交易密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwdDeal")
    @ResponseBody
    public AjaxResult resetPwdDeal(SysUser user) {
        userService.checkUserAllowed(user);
        SysUser user1 = userService.selectUserById(user.getUserId());
        if (true) {
            user.setFundPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user1.getSalt()));
            user.setPassword(null);
            if (userService.resetUserPwd(user) > 0) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
                return success();
            }
            return error();
        }
        return error();
    }

    /**
     * 删除用户
     *
     * @param ids 用户ID
     * @return 结果
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids, String check) {
        try {
            return toAjax(userService.deleteUserByIds(ids, check));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user) {
        userService.checkUserAllowed(user);
        return toAjax(userService.changeStatus(user));
    }

    /**
     * 用户Google验证器修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:bind")
    @PostMapping("/bind")
    @ResponseBody
    @Transactional
    public AjaxResult googleBind(HttpServletRequest request, SysUser user) {
        SysUser oUser = userService.selectUserById(user.getUserId());
        if ("1".equals(oUser.getStatus()))
            return AjaxResult.error("此用户已被停用，无法操作");
        if ("1".equals(user.getIsBind())) {//绑定
            //插入记录
            String secretKey = googleUtils.getSecretKey();
            StringBuffer url = request.getRequestURL();
            String host = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
            String QRUrl = googleUtils.getGoogleQRCodeURL(user.getLoginName(), host, secretKey);
            SysUserGoogle sysUserGoogle = new SysUserGoogle();
            sysUserGoogle.setLoginName(user.getLoginName());
            sysUserGoogle.setGoogleUrl(QRUrl);
            sysUserGoogle.setHost(host);
            sysUserGoogle.setSecretKey(secretKey);
            sysUserGoogle.setCreateBy(ShiroUtils.getLoginName());
            sysUserGoogle.setExpireTime(Long.parseLong("15"));
            int i = sysUserGoogleService.insertSysUserGoogle(sysUserGoogle);
            if (i == 1) {
                int j = userService.updateUserInfo(user);
                if (j == 1) {
                    return AjaxResult.success();
                } else {
                    return AjaxResult.error("绑定失败");
                }
            }
        }
        if ("0".equals(user.getIsBind())) { //解绑
            int i = sysUserGoogleService.deleteSysUserGoogleByLoginName(user.getLoginName());
            if (i == 1) {
                int j = userService.updateUserInfo(user);
                if (j == 1) {
                    return AjaxResult.success();
                } else {
                    return AjaxResult.error("解绑失败");
                }
            }
        }
        return null;
    }

    /**
     * 验证用户绑定google
     *
     * @return 返回结果
     */
    @PostMapping("/googleBind")
    @ResponseBody
    public AjaxResult bind(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        SysUserGoogle sysUserGoogle = sysUserGoogleService.selectSysUserGoogleByUsername(sysUser.getLoginName());
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
