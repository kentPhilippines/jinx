package com.ruoyi.web.controller.alipay;

import java.util.List;

import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.framework.util.DictionaryUtils;
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
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易订单Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/orderDeal")
public class AlipayDealOrderEntityController extends BaseController {
    private String prefix = "alipay/orderDeal";
    private String code_prefix = "alipay/file";
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private IAlipayDealOrderEntityService alipayDealOrderEntityService;

    @RequiresPermissions("alipay:orderDeal:view")
    @GetMapping()
    public String orderDeal() {
        return prefix + "/orderDeal";
    }

    /**
     * 查询交易订单列表
     */
    @RequiresPermissions("alipay:orderDeal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrderEntity alipayDealOrderEntity) {
        startPage();
        List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
                .selectAlipayDealOrderEntityList(alipayDealOrderEntity);
        return getDataTable(list);
    }


    @RequiresPermissions("alipay:orderDeal:updataOrder")
    @PostMapping("/updataOrder")
    @ResponseBody
    public AjaxResult updataOrder(String id) {
        AlipayDealOrderEntity order = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(Long.valueOf(id));
        order.setOrderStatus("7");//人工处理
        int i = alipayDealOrderEntityService.updateAlipayDealOrderEntity(order);
        return toAjax(i);
    }


    /**
     * 导出交易订单列表
     */
    @RequiresPermissions("alipay:orderDeal:export")
    @Log(title = "交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealOrderEntity alipayDealOrderEntity) {
        List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
                .selectAlipayDealOrderEntityList(alipayDealOrderEntity);
        ExcelUtil<AlipayDealOrderEntity> util = new ExcelUtil<AlipayDealOrderEntity>(AlipayDealOrderEntity.class);
        return util.exportExcel(list, "orderDeal");
    }

    /**
     * 新增交易订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存交易订单
     */
    @RequiresPermissions("alipay:orderDeal:add")
    @Log(title = "交易订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayDealOrderEntity alipayDealOrderEntity) {
        return toAjax(alipayDealOrderEntityService.insertAlipayDealOrderEntity(alipayDealOrderEntity));
    }

    /**
     * 修改交易订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderEntity alipayDealOrderEntity = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(id);
        mmap.put("alipayDealOrderEntity", alipayDealOrderEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存交易订单
     */
    @RequiresPermissions("alipay:orderDeal:edit")
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayDealOrderEntity alipayDealOrderEntity) {
        return toAjax(alipayDealOrderEntityService.updateAlipayDealOrderEntity(alipayDealOrderEntity));
    }

    /**
     * 显示二维码
     * @param imgId
     * @param mmap
     * @return
     */
    @GetMapping("/showCode/{imgId}")
    public String showCode(@PathVariable("imgId") String imgId, ModelMap mmap) {
        //获取二维码服务器地址
        String qrServerAddr = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_KEY, StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_VALUE);
        String qrServerPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_KEY, StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_PATH);
        mmap.put("imgUrl", qrServerAddr + qrServerPath + imgId);
        return code_prefix + "/view_code";
    }


    @RequiresPermissions("alipay:orderDeal:renotify")
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PostMapping("/renotify")
    @ResponseBody
    public AjaxResult renotify(AlipayDealOrderEntity alipayDealOrderEntity) {
        //调用通知方法

        return AjaxResult.error("未实现通知方法");
    }


}
