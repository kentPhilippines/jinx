package com.ruoyi.web.controller.home;

import com.ruoyi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/page")
public class HomePageController extends BaseController {

    @RequiresPermissions("home:page:view")
    @GetMapping()
    public String menu() {
        return "home";
    }

}
