package com.ruoyi.web.controller.alipay;

import cn.hutool.core.thread.ThreadUtil;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.HashKit;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 三方渠道管理Controller
 *
 * @author kent
 * @date 2020-05-01
 */
@Controller
@RequestMapping("/alipay/channel")
public class AipayChannelContorller extends BaseController {
    private String prefix = "alipay/channel";
    @Autowired private IAlipayUserInfoService alipayUserInfoService;
    @GetMapping()
    public String list() {
        return prefix + "/list";
    }

    @Autowired private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired private DictionaryUtils dictionaryUtils;
    @Autowired private IAlipayAmountEntityService alipayAmountEntityService;
    @Autowired private SysPasswordService passwordService;
    @Autowired private IAlipayChanelFeeService alipayChanelFeeService;

    /**
     * 查询用户资金账户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserFundEntity alipayUserFundEntity) {
        alipayUserFundEntity.setUserType("3");//渠道商户
        startPage();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .findChannelAccount(alipayUserFundEntity);
        return getDataTable(list);
    }

    /**
     * 新增渠道账户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }


    /**
     * 新增渠道
     */
    @Log(title = "渠道账户表", businessType = BusinessType.INSERT)
    @PostMapping("/addChannel")
    @ResponseBody
    public AjaxResult addSave(AlipayUserFundEntity fundEntity) {
        fundEntity.setUserType("3");//设置为渠道账号
        fundEntity.setIsAgent("3");//设置为渠道类型
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setUserId(fundEntity.getUserId());
        userInfo.setAgent("3");
        userInfo.setUserType(3);
        userInfo.setUserName(fundEntity.getUserName());
        String salt = HashKit.randomSalt();
        userInfo.setSalt(salt);
        userInfo.setUserNode(fundEntity.getUserNode());
        userInfo.setPassword(HashKit.encodePassword(userInfo.getUserId(), "123456", salt));
        userInfo.setPayPasword(HashKit.encodePassword(userInfo.getUserId(), "123456", salt));
        alipayUserInfoService.insertAlipayUserInfo(userInfo);
        return toAjax(alipayUserFundEntityService.insertAlipayUserFundEntity(fundEntity));
    }

    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap mmap) {
        String loginName = ShiroUtils.getLoginName();
        AlipayUserInfo channelInfo = alipayUserInfoService.findMerchantInfoByUserId(userId);
        mmap.put("channelInfo", channelInfo);
        mmap.put("userId", loginName);
        return prefix + "/edit";
    }

    /**
     * 转发财务
     */
    @Log(title = "渠道修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editChannelIfo(AlipayUserInfo channelInfo) {
        return toAjax(alipayUserInfoService.updateAlipayUserInfo(channelInfo));
    }

    /**
     * 转发财务
     */
    @Log(title = "渠道冷热门切换", businessType = BusinessType.INSERT)
    @PostMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(AlipayUserInfo channelInfo) {
        int i = alipayUserFundEntityService.updateStatus(channelInfo.getUserId(), channelInfo.getStatus());
        int i1 = alipayUserInfoService.updateStatus(channelInfo.getUserId(), channelInfo.getStatus());
        if (i > 0 && i1 > 0) {
            return success("修改成功");
        } else {
            ThreadUtil.execute(() -> {
                alipayUserFundEntityService.updateStatus(channelInfo.getUserId(), 1);
                alipayUserInfoService.updateStatus(channelInfo.getUserId(), 1);
            });
        }
        throw new BusinessException("操作失败，修改失败");
    }

    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;
    @Log(title = "用户详情", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        AlipayUserInfo  infoList = alipayUserInfoService.selectAlipayUserInfoById(Long.valueOf(ids));
        ThreadUtil.execute(()->{
            alipayUserInfoService.deleteUserById(infoList.getUserId());
            alipayUserFundEntityService.deleteUserById(infoList.getUserId());
            alipayRunOrderEntityService.deleteUserId(infoList.getUserId());
            alipayChanelFeeService.deleteChannelByChannel(infoList.getUserId());
        });
        return toAjax(1);

    }
}
