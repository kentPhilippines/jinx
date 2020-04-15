package com.ruoyi.web.controller.api;

import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.RSAUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.realm.UserRealm;
import com.ruoyi.system.domain.AdminAplipayDealOrder;
import com.ruoyi.system.domain.AdminDealpayDealOrder;
import com.ruoyi.system.service.IAdminAplipayDealOrderService;
import com.ruoyi.system.service.IAdminDealpayDealOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/order/record")
public class ApiGatewayController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(ApiGatewayController.class);

    @Autowired
    private IAdminDealpayDealOrderService adminDealpayDealOrderService;

    @Autowired
    private IAdminAplipayDealOrderService adminAplipayDealOrderService;

    @PostMapping("/alipay")
    @ResponseBody
    public AjaxResult recordAlipayOrder(HttpServletRequest request) {
        String cipherText = request.getParameter("cipherText");
        log.info("gateway后台传来的加密参数：{}", cipherText);
        if (StringUtils.isEmpty(cipherText)) {
            AjaxResult.error("加密参数为空");
        }
        Map<String, Object> map = RSAUtils.retMapDecode(cipherText, StaticConstants.INNER_PLATFORM_PUBLIC_KEY);
        AdminAplipayDealOrder adminAplipayDealOrder = MapDataUtil.mapToBean(map, AdminAplipayDealOrder.class);
        return toAjax(adminAplipayDealOrderService.insertAdminAplipayDealOrder(adminAplipayDealOrder));
    }


    @PostMapping("/dealpay")
    @ResponseBody
    public AjaxResult recordDealpayOrder(HttpServletRequest request) {
        String cipherText = request.getParameter("cipherTest");
        log.info("gateway后台传来的加密参数：{}", cipherText);
        if (StringUtils.isEmpty(cipherText)) {
            AjaxResult.error("加密参数为空");
        }
        Map<String, Object> map = RSAUtils.retMapDecode(cipherText, StaticConstants.INNER_PLATFORM_PUBLIC_KEY);
        AdminDealpayDealOrder adminDealpayDealOrder = MapDataUtil.mapToBean(map, AdminDealpayDealOrder.class);
        return toAjax(adminDealpayDealOrderService.insertAdminDealpayDealOrder(adminDealpayDealOrder));
    }


    public static void main(String[] args) {
        AdminAplipayDealOrder adminAplipayDealOrder = new AdminAplipayDealOrder();

        Map<String, Object> map = new HashMap<>();
        map.put("orderId",""); //平台订单
        map.put("associatedId","");//关联订单
        map.put("orderAccount","");//订单关联商户账号
        map.put("orderStatus","");//订单状态
        map.put("dealAmount","");//交易金额
        map.put("dealFee","");//手续费
        map.put("actualAmount","");//到账金额
        map.put("orderType","");//订单类型
        map.put("orderQrUser","");//关联码商账户
        map.put("orderQr","");//关联二维码
        map.put("fee","");//费率
        map.put("productType","");//产品类型
        map.put("createTime","");//创建时间



    }

}
