package com.ruoyi.web.controller.control;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayBankListEntityService;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dealpay.domain.DealpayBankListEntity;
import com.ruoyi.dealpay.service.IDealpayBankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商户风控Controller
 *
 * @author ruoyi
 * @date 2020-03-23
 */
@Controller
@RequestMapping("/control/account")
public class MerchantControlController extends BaseController {

    private String prefix = "control/account";
    private String bankcardControl_prefix = "control/bankCard";

    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;

    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    @Autowired
    private IAlipayBankListEntityService alipayBankListEntityService;

    @Autowired
    private IDealpayBankListService dealpayBankListService;

    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }

    /**
     * 查询商户风控信息列表
     */
    @PostMapping("/merchant/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserInfo merchantInfoEntity) {
        startPage();
        List<AlipayUserInfo> list = merchantInfoEntityService.selectMerchantControlList(merchantInfoEntity);
        return getDataTable(list);
    }

    /**
     * 显示商户风控信息
     */
    @GetMapping("/merchant/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = merchantInfoEntityService.selectMerchantInfoEntityById(id);
        mmap.put("alipayUserInfo", userInfo);
        return prefix + "/merchant_edit";
    }

    //如果请求alipay接口走下面的方法
//    /**
//     * 修改保存商户信息
//     */
//    @RequiresPermissions("alipay:merchant:edit")
//    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(AlipayUserInfo merchantInfoEntity) {
//        //获取alipay处理接口URL
//        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
//        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_ERVICE_API_KEY, StaticConstants.ALIPAY_ERVICE_API_VALUE);
//        //获取数据库内请求路径
//        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
//        mapParam.put("id", merchantInfoEntity.getId());
//        mapParam.put("userId", merchantInfoEntity.getUserId());
//        mapParam.put("userName", merchantInfoEntity.getUserName());
//        mapParam.put("email", merchantInfoEntity.getEmail());
//        mapParam.put("QQ", merchantInfoEntity.getQQ());
//        mapParam.put("telegram", merchantInfoEntity.getTelegram());
//        mapParam.put("userNode", merchantInfoEntity.getUserNode());
//        AlipayUserInfo alipayUserInfo = merchantInfoEntityService.selectMerchantInfoEntityById(merchantInfoEntity.getId());
//        //对参数进行RSA加密处理
//        String cipherText = RSAUtils.getEncryptPublicKey(mapParam, alipayUserInfo.getPublicKey());
//        String pathParam = "userId=" + merchantInfoEntity.getUserId() + "&cipherText=" + cipherText;
//        //post调用alipay方法
//        String flag = HttpUtils.sendPost(ipPort + urlPath, pathParam);
//        if ("ConnectException".equals(flag)) {
//            throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + ipPort + urlPath);
//        }
//        JSONObject json = JSONObject.parseObject(flag);
//        String result = json.getString("success");
//        switch (result) {
//            case "true":
//                return toAjax(1);
//            case "false":
//                String message = json.getString("message");
//                return error(message);
//        }
//        return null;
//    }

    /**
     * 修改保存商户风控信息
     */
    @Log(title = "商户风控", businessType = BusinessType.UPDATE)
    @PostMapping("/merchant/save")
    @ResponseBody
    public AjaxResult editSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(merchantInfoEntityService.updateMerchantInfoEntity(alipayUserInfo));
    }

    /**
     * 显示码商列表
     *
     * @return
     */
    @GetMapping("/qr/show")
    public String qr() {
        return prefix + "/qr";
    }

    /**
     * 查询码商信息列表
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
     * 显示码商风控信息
     */
    @GetMapping("/qr/edit/{id}")
    public String qrEdit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = merchantInfoEntityService.selectMerchantInfoEntityById(id);
        mmap.put("alipayUserInfo", userInfo);
        return prefix + "/qr_edit";
    }

    /**
     * 修改保存商户风控信息
     */
    @Log(title = "商户风控", businessType = BusinessType.UPDATE)
    @PostMapping("/qr/save")
    @ResponseBody
    public AjaxResult qrSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(merchantInfoEntityService.updateAlipayUserInfoDealUrlByObj(alipayUserInfo));
    }


    /*码商银行卡黑名单处理逻辑*/

    @GetMapping("/bankCard/qr/view")
    public String bank() {
        return bankcardControl_prefix + "/qrBankCard";
    }

    /**
     *
     * 查询码商银行卡黑名单
     */
    @PostMapping("/bankCard/qr/list")
    @ResponseBody
    public TableDataInfo list(AlipayBankListEntity alipayBankListEntity) {
        startPage();
        alipayBankListEntity.setSysTYpe(1);//黑卡
        alipayBankListEntity.setCardType(2);//码商银行卡
        List<AlipayBankListEntity> list = alipayBankListEntityService.selectAlipayBankListEntityList(alipayBankListEntity);
        return getDataTable(list);
    }

    /**
     * 新增银行卡黑名单
     */
    @GetMapping("/add/show/bankCard/page/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap) {
        mmap.put("flag",id);
        return bankcardControl_prefix + "/add";
    }
    /**
     * 保存银行卡黑名单
     */
    @Log(title = "码商银行卡", businessType = BusinessType.INSERT)
    @PostMapping("/add/qr/black")
    @ResponseBody
    public AjaxResult editSave(AlipayBankListEntity alipayBankListEntity) {
        return toAjax(alipayBankListEntityService.updateAlipayBankCardBlackList(alipayBankListEntity));
    }

    /**
     * 删除银行卡黑名单
     */
    @Log(title = "码商银行卡", businessType = BusinessType.DELETE)
    @PostMapping("/remove/bankCard/qr")
    @ResponseBody
    public AjaxResult removeBnakQr(String ids) {
        return toAjax(alipayBankListEntityService.deleteAlipayBankBlackListById(ids));
    }

    /*商户银行卡黑名单处理逻辑*/
    @GetMapping("/bankCard/merchant/view")
    public String merchantBankCard() {
        return bankcardControl_prefix + "/merchantBankCard";
    }

    /**
     *
     * 查询码商银行卡黑名单
     */
    @PostMapping("/bankCard/merchant/list")
    @ResponseBody
    public TableDataInfo merchantBanklist(AlipayBankListEntity alipayBankListEntity) {
        startPage();
        alipayBankListEntity.setSysTYpe(1);//黑卡
        alipayBankListEntity.setCardType(3);//商户银行卡
        List<AlipayBankListEntity> list = alipayBankListEntityService.selectAlipayBankListEntityList(alipayBankListEntity);
        return getDataTable(list);
    }

    /**
     * 保存商户银行卡黑名单
     */
    @Log(title = "商户银行卡", businessType = BusinessType.INSERT)
    @PostMapping("/add/merchant/black")
    @ResponseBody
    public AjaxResult editMerchantSave(AlipayBankListEntity alipayBankListEntity) {
        return toAjax(alipayBankListEntityService.updateAlipayBankCardBlackList(alipayBankListEntity));
    }

    /**
     * 删除银行卡黑名单
     */
    @Log(title = "商户银行卡", businessType = BusinessType.DELETE)
    @PostMapping("/remove/bankCard/merchant")
    @ResponseBody
    public AjaxResult removeBankMerchant(String ids) {
        return toAjax(alipayBankListEntityService.deleteAlipayBankBlackListById(ids));
    }

    /*卡商银行卡黑名单处理逻辑*/
    @GetMapping("/bankCard/card/view")
    public String cardBankCard() {
        return bankcardControl_prefix + "/payforCard";
    }

    /**
     *
     * 查询卡商银行卡黑名单
     */
    @PostMapping("/bankCard/payfor/list")
    @ResponseBody
    public TableDataInfo payforBanklist(DealpayBankListEntity dealpayBankListEntity) {
        startPage();
        dealpayBankListEntity.setSysTYpe(1);//黑卡
        List<DealpayBankListEntity> list = dealpayBankListService.selectDealpayBankListList(dealpayBankListEntity);
        return getDataTable(list);
    }
    /**
     * 保存商户银行卡黑名单
     */
    @Log(title = "商户银行卡", businessType = BusinessType.INSERT)
    @PostMapping("/add/payfor/black")
    @ResponseBody
    public AjaxResult editPayforSave(DealpayBankListEntity dealpayBankListEntity) {
        return toAjax(dealpayBankListService.updateDealpayBankCardBlackList(dealpayBankListEntity));
    }

    /**
     * 删除卡商银行卡黑名单
     */
    @Log(title = "卡商银行卡", businessType = BusinessType.DELETE)
    @PostMapping("/remove/bankCard/payfor")
    @ResponseBody
    public AjaxResult removeBankpayfor(String ids) {
        return toAjax(dealpayBankListService.deleteDealpayBankBlackListById(ids));
    }

    @Log(title = "修改商户自动代付状态", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAutoWit")
    @ResponseBody
    public AjaxResult updateAutoWit(String id, String autoWitStatus) {
        return toAjax(alipayUserInfoService.updateAutoWit(id, autoWitStatus));
    }


}
