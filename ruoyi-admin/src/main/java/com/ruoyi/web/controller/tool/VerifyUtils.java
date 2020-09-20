package com.ruoyi.web.controller.tool;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.GoogleUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/verify/identity")
public class VerifyUtils {

    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private GoogleUtils googleUtils;

    public AjaxResult verifyPwdAndGoogle(String fundPassword, String googleCode) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), fundPassword, currentUser.getSalt());
        if (!currentUser.getFundPassword().equals(verify)) {
            return AjaxResult.error("资金管理密码验证失败");
        }
        //验证谷歌验证码
        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
        if (is == 0) {
            return AjaxResult.error("未绑定谷歌验证器，请联系管理员");
        } else if (is - 1 > 0) {
            return AjaxResult.error("谷歌动态口令验证失败");
        }
        return AjaxResult.success();
    }


    /**
     * 只验证谷歌验证
     *
     * @param googleCode
     * @return
     */
    @GetMapping("/google")
    @ResponseBody
    public AjaxResult verifyGoogle(String googleCode) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        //验证谷歌验证码
        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
        if (is == 0) {
            return AjaxResult.error("未绑定谷歌验证器，请联系管理员");
        } else if (is - 1 > 0) {
            return AjaxResult.error("谷歌动态口令验证失败");
        }
        return AjaxResult.success();
    }

    /**
     * admin操作绑定验证
     *
     * @param googleCode
     * @return
     */
    @PostMapping("/admin/google")
    @ResponseBody
    public AjaxResult adminVerifyGoogle(String loginName, String googleCode) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser.isAdmin() && "admin".equals(loginName)) {
            return AjaxResult.success();
        }
        //验证谷歌验证码
        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
        if (is == 0) {
            return AjaxResult.error("未绑定谷歌验证器，请联系管理员");
        } else if (is - 1 > 0) {
            return AjaxResult.error("谷歌动态口令验证失败");
        }
        return AjaxResult.success();
    }


}
