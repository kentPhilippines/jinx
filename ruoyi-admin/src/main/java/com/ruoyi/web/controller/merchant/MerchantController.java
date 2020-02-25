package com.ruoyi.web.controller.merchant;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/merchant/info")
public class MerchantController extends BaseController {
    private String prefix = "/merchant/info/";

    @GetMapping()
    public String info() {
        return "merchant/info/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        startPage();
//        List<> list = logininforService.selectLogininforList(logininfor);
//        return getDataTable(list);
        return null;
    }

    @GetMapping("/add")
    public String showAdd() {
//        return "merchant/info/add";
//        return "qrOwner/info/add";
        return "merchant_agent/info/add";
    }

}
