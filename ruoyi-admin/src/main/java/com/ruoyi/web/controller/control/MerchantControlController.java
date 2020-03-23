package com.ruoyi.web.controller.control;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 商户风控Controller
 *
 * @author ruoyi
 * @date 2020-03-23
 */
@Controller
@RequestMapping("/control/merchant")
public class MerchantControlController extends BaseController {

    private String prefix = "control/merchant";

    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;

    @RequiresPermissions("control:merchant:view")
    @GetMapping()
    public String control() {
        return prefix + "/list";
    }

    /**
     * 查询商户风控信息列表
     */
    @RequiresPermissions("control:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserInfo merchantInfoEntity) {
        startPage();
        List<AlipayUserInfo> list = merchantInfoEntityService.selectMerchantControlList(merchantInfoEntity);
        return getDataTable(list);
    }

}
