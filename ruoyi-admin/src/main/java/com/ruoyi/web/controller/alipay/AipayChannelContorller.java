package com.ruoyi.web.controller.alipay;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.HashKit;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

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
     * 新增保存银行卡列表
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
        userInfo.setPassword(HashKit.encodePassword(userInfo.getUserId(),"123456",salt));
        userInfo.setPayPasword(HashKit.encodePassword(userInfo.getUserId(),"123456",salt));
        alipayUserInfoService.insertAlipayUserInfo(userInfo);
        return toAjax(alipayUserFundEntityService.insertAlipayUserFundEntity(fundEntity));
    }
}
