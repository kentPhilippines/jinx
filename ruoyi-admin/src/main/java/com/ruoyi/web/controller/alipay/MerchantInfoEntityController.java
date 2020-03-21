package com.ruoyi.web.controller.alipay;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.common.utils.NoUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户信息Controller
 *
 * @author ruoyi
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/merchant")
public class MerchantInfoEntityController extends BaseController {
    private String prefix = "alipay/merchant/info";
    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;
    @RequiresPermissions("alipay:merchant:view")
    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }
    /**
     * 查询商户信息列表
     */
    @RequiresPermissions("alipay:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserInfo merchantInfoEntity) {
        startPage();
        List<AlipayUserInfo> list = merchantInfoEntityService.selectMerchantInfoEntityList(merchantInfoEntity);
        return getDataTable(list);
    }
    /**
     * 新增商户信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商户信息
     */
    @RequiresPermissions("alipay:merchant:add")
    @Log(title = "商户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserInfo merchantInfoEntity) {
        return toAjax(merchantInfoEntityService.insertMerchantInfoEntity(merchantInfoEntity));
    }

    /**
     * 修改商户信息
     * 
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = merchantInfoEntityService.selectMerchantInfoEntityById(id);
        mmap.put("alipayUserInfo", userInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户信息
     */
    @RequiresPermissions("alipay:merchant:edit")
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerchantInfoEntity merchantInfoEntity) {
        return toAjax(merchantInfoEntityService.updateMerchantInfoEntity(merchantInfoEntity));
    }

    /**
     * 删除商户信息
     */
    @RequiresPermissions("alipay:merchant:remove")
    @Log(title = "商户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(merchantInfoEntityService.deleteMerchantInfoEntityByIds(ids));
    }

    /**
     * 自动生成商户编码
     *
     * @return
     */
    @PostMapping("/getMerchantNo")
    @ResponseBody
    public AjaxResult getMerchantNo() {
        return AjaxResult.success(NoUtils.shortUUID());
    }

}
