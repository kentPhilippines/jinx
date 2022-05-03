package com.ruoyi.web.controller.alipay;

import com.ruoyi.alipay.domain.AlipayConfirmRewardConfig;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayConfirmRewardConfigService;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代付确认奖励配置Controller
 * 
 * @author ruoyi
 * @date 2022-05-03
 */
@Controller
@RequestMapping("/reward/config")
public class AlipayConfirmRewardConfigController extends BaseController
{
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    private String prefix = "alipay/config";

    @Autowired
    private IAlipayConfirmRewardConfigService alipayConfirmRewardConfigService;

    @RequiresPermissions("system:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询代付确认奖励配置列表
     */
    @RequiresPermissions("system:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        startPage();
        List<AlipayConfirmRewardConfig> list = alipayConfirmRewardConfigService.selectAlipayConfirmRewardConfigList(alipayConfirmRewardConfig);
        return getDataTable(list);
    }

    /**
     * 导出代付确认奖励配置列表
     */
    @RequiresPermissions("system:config:export")
    @Log(title = "代付确认奖励配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        List<AlipayConfirmRewardConfig> list = alipayConfirmRewardConfigService.selectAlipayConfirmRewardConfigList(alipayConfirmRewardConfig);
        ExcelUtil<AlipayConfirmRewardConfig> util = new ExcelUtil<AlipayConfirmRewardConfig>(AlipayConfirmRewardConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增代付确认奖励配置
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        AlipayUserInfo alipayUserInfo = new AlipayUserInfo();
        //查询所有的商户
        alipayUserInfo.setSwitchs(1);
        alipayUserInfo.setUserType(1);
        List<AlipayUserInfo> userInfo = alipayUserInfoService.selectAllUserInfoList(alipayUserInfo);
        modelMap.put("merList", userInfo);
        return prefix + "/add";
    }

    /**
     * 新增保存代付确认奖励配置
     */
    @RequiresPermissions("system:config:add")
    @Log(title = "代付确认奖励配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        return toAjax(alipayConfirmRewardConfigService.insertAlipayConfirmRewardConfig(alipayConfirmRewardConfig));
    }

    /**
     * 修改代付确认奖励配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AlipayConfirmRewardConfig alipayConfirmRewardConfig = alipayConfirmRewardConfigService.selectAlipayConfirmRewardConfigById(id);
        mmap.put("alipayConfirmRewardConfig", alipayConfirmRewardConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存代付确认奖励配置
     */
    @RequiresPermissions("system:config:edit")
    @Log(title = "代付确认奖励配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        return toAjax(alipayConfirmRewardConfigService.updateAlipayConfirmRewardConfig(alipayConfirmRewardConfig));
    }

    /**
     * 删除代付确认奖励配置
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "代付确认奖励配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(alipayConfirmRewardConfigService.deleteAlipayConfirmRewardConfigByIds(ids));
    }
}
