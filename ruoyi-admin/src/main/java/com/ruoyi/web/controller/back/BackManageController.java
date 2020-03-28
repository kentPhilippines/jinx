package com.ruoyi.web.controller.back;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("back/merchant/admin")
@Controller
public class BackManageController extends BaseController {
    private String prefix = "/merchant/info";

    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;

    /**
     * 商户后台用户登陆显示详细信息
     */
    @RequiresPermissions("back:merchant:view")
    @GetMapping()
    public String detail(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayUserInfo userInfo = merchantInfoEntityService.selectBackUserByUserId(sysUser.getMerchantId());
        if (userInfo == null)
            throw new BusinessException("此商户不存在");
        mmap.put("userInfo", userInfo);
        return prefix + "/detail";
    }


    /**
     *  商户保存修改信息
     * @param alipayUserInfo
     * @return
     */
    @RequiresPermissions("back:merchant:edit")
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult toSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(merchantInfoEntityService.updateMerchantByBackAdmin(alipayUserInfo));
    }

}
