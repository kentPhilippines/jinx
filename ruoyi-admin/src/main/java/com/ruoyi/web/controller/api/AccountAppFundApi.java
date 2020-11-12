package com.ruoyi.web.controller.api;


import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 自动对账
 */
@Controller
@RequestMapping("/accountAppFundApi")
public class AccountAppFundApi extends BaseController {
    private String prefix = "alipay/accountAppFund";
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;

    @GetMapping()
    public String fund() {
        return prefix + "list";
    }

    @PostMapping("/accountList")
    @ResponseBody
    public TableDataInfo accountList(AlipayUserFundEntity alipayUserFundEntity) {
        startPage();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .findaAcountList(alipayUserFundEntity);
        return getDataTable(list);
    }


}
