package com.ruoyi.web.controller.back;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/back/merchant/post")
public class BackPostController extends BaseController {

    private final static String prefix = "merchant/back/post";

    @Autowired
    private ISysPostService postService;

    /**
     * 跳转显示页面
     */
    @RequiresPermissions("back:merchant:post:view")
    @GetMapping()
    public String operlog() {
        SysUser currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isEmpty(currentUser.getMerchantId())) {
            throw new BusinessException("非商户后台用户，不允许操作,请联系平台管理员");
        }
        return prefix + "/post";
    }

    /**
     * 显示岗位列表
     */
    @RequiresPermissions("back:merchant:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPost post) {
        SysUser currentUser = ShiroUtils.getSysUser();
        post.setMerchantId(currentUser.getMerchantId());
        startPage();
        List<SysPost> list = postService.backSelectPostByMerchantId(post);
        return getDataTable(list);
    }

    /**
     * 新增岗位
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存岗位
     */
    @RequiresPermissions("back:merchant:post:add")
    @Log(title = "子账户岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysPost post) {
        SysUser currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(currentUser.getMerchantId())) {
            post.setMerchantId(currentUser.getMerchantId());
        }
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setAccountType(1);
        post.setCreateBy(currentUser.getLoginName());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap mmap) {
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("back:merchant:post:edit")
    @Log(title = "子账户岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysPost post) {
        SysUser currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(currentUser.getMerchantId())) {
            post.setMerchantId(currentUser.getMerchantId());
        }
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setAccountType(null);//不升级账户类型
        post.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 岗位删除
     */
    @RequiresPermissions("back:merchant:post:remove")
    @Log(title = "子账户岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(postService.deletePostByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

}
