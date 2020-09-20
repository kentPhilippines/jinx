package com.ruoyi.web.controller.back;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.GoogleUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/back/merchant/user")
public class BackUserController extends BaseController {
    String prefix = "merchant/back/user";
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private GoogleUtils googleUtils;

    @Autowired
    private ISysUserGoogleService sysUserGoogleService;

    /**
     * 显示列表页面
     */
    @RequiresPermissions("back:merchant:user:view")
    @GetMapping()
    public String user(SysDept sysDept, ModelMap mmap) {
        SysUser currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isEmpty(currentUser.getMerchantId())) {
            throw new BusinessException("非商户后台用户，不允许操作,请联系平台管理员");
        }
        sysDept.setMerchantId(currentUser.getMerchantId());
        sysDept.setAccountType(1);
        mmap.put("depts", deptService.backSelectDeptListByMerchantId(sysDept));
        return prefix + "/user";
    }

    /**
     * 商户管理员查詢商戶列表
     */
    @RequiresPermissions("back:merchant:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        SysUser currentUser = ShiroUtils.getSysUser();
        user.setMerchantId(currentUser.getMerchantId());
        startPage();
        List<SysUser> list = userService.backSelectUserList(user);
        return getDataTable(list);
    }

    /**
     * 商户管理员新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysRole sysRole = new SysRole();
        SysPost sysPost = new SysPost();
        SysDept sysDept = new SysDept();
        sysRole.setMerchantId(currentUser.getMerchantId());
        sysPost.setMerchantId(currentUser.getMerchantId());
        sysDept.setMerchantId(currentUser.getMerchantId());
        sysDept.setAccountType(0);
        mmap.put("roles", roleService.backSelectRolesByMerchantId(sysRole));
        mmap.put("posts", postService.backSelectPostByMerchantId(sysPost));
        mmap.put("dept", deptService.backSelectDeptById(sysDept));
        return prefix + "/add";
    }

    /**
     * 商户管理员新增保存用户
     */
    @RequiresPermissions("back:merchant:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user) {
        //验证谷歌验证码
        SysUser currentUser = ShiroUtils.getSysUser();
        String googleCode = user.getParams().get("googleCode").toString();
        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
        if (is == 0) {
            return AjaxResult.error("未绑定谷歌验证器，请联系管理员");
        } else if (is - 1 > 0) {
            return AjaxResult.error("谷歌动态口令验证失败");
        }
        user.setMerchantId(currentUser.getMerchantId());
        user.setAccountType(1);//表示商户账户的子账户
        String prefix = user.getLoginName().substring(0, 4);
        if ("1000".equals(prefix) || "3000".equals(prefix) || "2000".equals(prefix)) {
            return error("商户子账户登陆账号禁用1000，2000，3000号段");
        }
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        /*else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }*/
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setFundPassword(passwordService.encryptPassword(user.getLoginName(), user.getFundPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(userService.backInsertUserByMerchantId(user));
    }

    /**
     * 商户管理员修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept sysDept = new SysDept();
        sysDept.setMerchantId(currentUser.getMerchantId());
        sysDept.setAccountType(0);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.backFindCheckedRolesById(userId, currentUser.getMerchantId()));
        mmap.put("posts", postService.backFindCheckedPostsById(userId, currentUser.getMerchantId()));
        mmap.put("dept", deptService.backSelectDeptById(sysDept));
        return prefix + "/edit";
    }

    /**
     * 商户管理员修改保存用户
     */
    @RequiresPermissions("back:merchant:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user) {
//        userService.checkUserAllowed(user);
//        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
//            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
//        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
//            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
//        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     *
     * @param ids 用户ID
     * @return 结果
     */
    @RequiresPermissions("back:merchant:user:remove")
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

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    /**
     * 重置登陆密码
     *
     * @param user
     * @return
     */
    @RequiresPermissions("back:merchant:resetPwd")
    @Log(title = "商户重置登陆密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        final String pwd = user.getPassword();
        userService.checkUserAllowed(user);
        SysUser changeUser = userService.selectUserById(user.getUserId());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), changeUser.getSalt()));
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId() == user.getUserId()) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @GetMapping("/resetFundPwd/{userId}")
    public String fundPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetFundPwd";
    }


    /**
     * 重置资金管理密码
     *
     * @param user
     * @return
     */
    @RequiresPermissions("back:merchant:fundPwd")
    @Log(title = "商户重置资金密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetFundPwd")
    @ResponseBody
    public AjaxResult fundPwdSave(SysUser user) {
        final String fpwd = user.getFundPassword();
        userService.checkUserAllowed(user);
        SysUser oUser = userService.selectUserByLoginName(user.getLoginName());
        user.setFundPassword(passwordService.encryptPassword(user.getLoginName(), user.getFundPassword(), oUser.getSalt()));
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId() == user.getUserId()) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 用户Google验证器修改
     */
    @RequiresPermissions("merchant:user:bind")
    @Log(title = "商户绑定谷歌", businessType = BusinessType.UPDATE)
    @PostMapping("/bind")
    @ResponseBody
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
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("back:merchant:status")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user) {
        userService.checkUserAllowed(user);
        return toAjax(userService.changeStatus(user));
    }
}
