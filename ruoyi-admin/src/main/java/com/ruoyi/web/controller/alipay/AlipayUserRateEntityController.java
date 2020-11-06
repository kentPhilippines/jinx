package com.ruoyi.web.controller.alipay;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户产品费率Controller
 * 为什么不到业务层写业务代码，因为多数据源切的是业务层，数据获取灵活度不高，所以业务层写业务不好弄
 *
 * @author kent
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/rate")
public class AlipayUserRateEntityController extends BaseController {
    private String prefix = "alipay/merchant/rate";
    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    IAlipayProductService iAlipayProductService;
    @Autowired
    IAlipayChanelFeeService alipayChanelFeeServiceImpl;
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;
    /*
     * 查询商户产品费率列表
     */
    static final String PAY_TYPE = "1";

    @RequiresPermissions("merchant:rate:view")
    @GetMapping()
    public String rate(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayUserFundEntity> channelList = alipayUserFundEntityService.findUserFundRate();
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        modelMap.put("channelList", channelList);
        return prefix + "/rate";
    }

    static final String MSG = "【当交易额为1000时盈利：";
    @Autowired
    private IAlipayChanelFeeService alipayChanelFeeService;

    @RequiresPermissions("merchant:rate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserRateEntity alipayUserRateEntity) {
        startPage();
        List<AlipayUserRateEntity> list = alipayUserRateEntityService.selectAlipayUserRateEntityList(alipayUserRateEntity);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayUserFundEntity> qrCollect = rateList.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        BigDecimal a = new BigDecimal("0");
        for (AlipayUserRateEntity rate : list) {
            AlipayUserFundEntity channel = qrCollect.get(rate.getChannelId());
            AlipayProductEntity product = prCollect.get(rate.getPayTypr());
            AlipayChanelFee channelBy = alipayChanelFeeService.findChannelBy(rate.getChannelId(), rate.getPayTypr());
            String channelRFee = channelBy.getChannelRFee();
            a = new BigDecimal("" + rate.getFee());
            if (rate.getFeeType().toString().equals(PAY_TYPE)) {
                rate.setChannelFee(channelRFee);
                rate.setProfit(String.valueOf(a.subtract(new BigDecimal(channelRFee))));
            } else {
                rate.setChannelFee(channelBy.getChannelDFee());
                rate.setProfit(String.valueOf(a.subtract(new BigDecimal(channelBy.getChannelDFee()))));
            }
            if (ObjectUtil.isNotNull(channel))
                rate.setChannelId(channel.getUserName());
            if (ObjectUtil.isNotNull(product))
                rate.setPayTypr(product.getProductName());
        }
        return getDataTable(list);
    }

    /*
     * 新增用户产品费率
     */
    @Log(title = "新增商户费率", businessType = BusinessType.INSERT)
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        AlipayUserInfo alipayUserInfo = new AlipayUserInfo();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        modelMap.put("rateList", rateList);
        //查询所有的商户
        alipayUserInfo.setSwitchs(1);
        alipayUserInfo.setUserType(1);
        List<AlipayUserInfo> userInfo = alipayUserInfoService.selectAllUserInfoList(alipayUserInfo);
        modelMap.put("merList", userInfo);
        return prefix + "/add";
    }


    /*
     * 新增保存用户产品费率
     */
    @RequiresPermissions("merchant:rate:add")
    @Log(title = "商户费率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserRateEntity alipayUserRateEntity) {
        AlipayChanelFee channel = alipayChanelFeeServiceImpl.findChannelBy(alipayUserRateEntity.getChannelId(), alipayUserRateEntity.getPayTypr());
        if (ObjectUtil.isNull(channel))
            return error("当前渠道未接通，请联系技术人员对接");
        AlipayUserRateEntity check = alipayUserRateEntityService.checkUniqueRate(alipayUserRateEntity);
        if (null != check) {
            throw new BusinessException("操作失败，商户费率重复");
        }
        return toAjax(alipayUserRateEntityService.insertAlipayUserRateEntity(alipayUserRateEntity));
    }

    /*
     * 修改用户产品费率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityService.selectAlipayUserRateEntityById(id);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        mmap.put("rateList", rateList);
        mmap.put("alipayUserRateEntity", alipayUserRateEntity);
        return prefix + "/edit";
    }

    /*
     * 修改保存用户产品费率
     */
    @RequiresPermissions("merchant:rate:edit")
    @Log(title = "商户费率", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserRateEntity alipayUserRateEntity) {
        //1，查看当前修改完的费率是否有配置渠道费率,并检查是否有重复配置的情况
        //2，查看当前修完费率是否有配置代理商费率
        //3，如以上不存在问题，则保存当前修改完费率，且对相同产品类型的费率进行关闭
        alipayUserRateEntityService.clickFee(alipayUserRateEntity);
        alipayUserRateEntityService.isAgentFee(alipayUserRateEntity);
        AlipayChanelFee channel = alipayChanelFeeServiceImpl.findChannelBy(alipayUserRateEntity.getChannelId(), alipayUserRateEntity.getPayTypr());
        if (ObjectUtil.isNull(channel))
            return error("当前渠道未接通，请联系技术人员对接");
        return toAjax(alipayUserRateEntityService.updateAlipayUserRateEntity(alipayUserRateEntity));
    }

    /*
     * 删除用户产品费率
     */
    @RequiresPermissions("merchant:rate:remove")
    @Log(title = "商户费率", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayUserRateEntityService.deleteAlipayUserRateEntityByIds(ids));
    }

    /**
     * 商户费率状态更新
     */
    @RequiresPermissions("merchant:rate:status")
    @Log(title = "商户费率", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult updateStatus(String id, String userId, String feeType, String switchs) {
        return toAjax(alipayUserRateEntityService.changeStatus(id, userId, feeType, switchs));
    }

}
