package com.ruoyi.web.controller.control;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;
import com.ruoyi.dealpay.service.IDealpayUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/control/service")
public class ServiceControlController extends BaseController {

    private String prefix = "control/service";

    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    @Autowired
    private IDealpayUserInfoService dealpayUserInfoService;


    @GetMapping("/qr")
    public String qrControl() {
        return prefix + "/qr";
    }

    /**
     * 查询码商顶代风控信息列表
     */
    @PostMapping("/qr/list")
    @ResponseBody
    public TableDataInfo qrList(AlipayUserInfo alipayUserInfo) {
        startPage();
        alipayUserInfo.setUserType(2);
        List<AlipayUserInfo> list = alipayUserInfoService.selectAlipayUserInfoByControl(alipayUserInfo);
        return getDataTable(list);
    }

    /**
     * 显示码商风控卡商服务群信息
     */
    @GetMapping("/qr/edit/{id}")
    public String qrEdit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = alipayUserInfoService.selectAlipayUserInfoById(id);
        DealpayUserInfoEntity dealpayUserInfoEntity = new DealpayUserInfoEntity();
        List<DealpayUserInfoEntity> list = dealpayUserInfoService.selectdealpayUserInfoByAgent(dealpayUserInfoEntity);
        if (userInfo == null) {
            throw new BusinessException("此用户不存在");
        }
        if (StringUtils.isEmpty(userInfo.getQrRechargeList())) {
            mmap.put("cardInfo", list);
        } else {
            String[] str = userInfo.getQrRechargeList().split(",");
            List<String> cardList = Arrays.asList(str);
            for (DealpayUserInfoEntity item : list) {
                if (cardList.contains(item.getUserId())) {
                    item.setCheckFlag(true);
                }
            }
            mmap.put("cardInfo",list);
        }
        mmap.put("alipayInfo", userInfo);
        return prefix + "/qr_edit";
    }

    /**
     * 保存码商风控卡商服务群信息
     */
    @Log(title = "码商服务群", businessType = BusinessType.UPDATE)
    @PostMapping("/qr/save")
    @ResponseBody
    public AjaxResult qrSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(alipayUserInfoService.toSaveQrChargeList(alipayUserInfo));
    }

    @GetMapping("/merchant")
    public String control() {
        return prefix + "/merchant";
    }

    /**
     * 查询商户顶代风控信息列表
     */
    @PostMapping("/merchant/list")
    @ResponseBody
    public TableDataInfo merchantList(AlipayUserInfo alipayUserInfo) {
        startPage();
        alipayUserInfo.setUserType(1);
        List<AlipayUserInfo> list = alipayUserInfoService.selectAlipayUserInfoByControl(alipayUserInfo);
        return getDataTable(list);
    }

    /**
     * 显示商户风控卡商服务群信息
     */
    @GetMapping("/merchant/edit/{id}")
    public String merchantEdit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = alipayUserInfoService.selectAlipayUserInfoById(id);
        DealpayUserInfoEntity dealpayUserInfoEntity = new DealpayUserInfoEntity();
       // List<DealpayUserInfoEntity> list = dealpayUserInfoService.selectdealpayUserInfoByAgent(dealpayUserInfoEntity);
        List<AlipayUserInfo> arlist = alipayUserInfoService.selectdealpayUserInfoByAgent();
        if (userInfo == null) {
            throw new BusinessException("此用户不存在");
        }
       /* if (StringUtils.isEmpty(userInfo.getQrRechargeList())) {//为空直接返回全部列表
            mmap.put("cardInfo", list);
        } else {//不为空判断选中状态
            String[] str = userInfo.getQrRechargeList().split(",");
            List<String> cardList = Arrays.asList(str);
            for (DealpayUserInfoEntity item : list) {
                if (cardList.contains(item.getUserId())) {
                    item.setCheckFlag(true);
                }
            }
            mmap.put("cardInfo",list);
        }*/

        if(StringUtils.isEmpty(userInfo.getQueueList())){
            mmap.put("qrInfo",arlist);
        } else {
            String[] str = userInfo.getQueueList().split(",");
            List<String> qrlist = Arrays.asList(str);
            for (AlipayUserInfo item : arlist) {
                if (qrlist.contains(item.getUserId())) {
                    item.setCheckFlag(true);
                }
            }
            mmap.put("qrInfo",arlist);
        }
        mmap.put("alipayInfo", userInfo);
        return prefix + "/merchant_edit";
    }

    /**
     * 保存商户风控卡商服务群信息
     */

    @Log(title = "商户服务群", businessType = BusinessType.UPDATE)
    @PostMapping("/merchant/save")
    @ResponseBody
    public AjaxResult merchantSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(alipayUserInfoService.toSaveQrChargeList(alipayUserInfo));
    }

}
