package com.ruoyi.web.controller.back;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/back/merchant/role")
public class BackRoleController extends BaseController {
    private final static String prefix = "merchant/back/role";

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService userService;

    /**
     * 商户跳转页面
     */
    @RequiresPermissions("back:merchant:role:view")
    @GetMapping()
    public String role() {
        SysUser currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isEmpty(currentUser.getMerchantId())) {
            throw new BusinessException("非商户后台用户，不允许操作,请联系平台管理员");
        }
        return prefix + "/role";
    }

    /**
     * 商户账户查询角色列表
     */
    @RequiresPermissions("back:merchant:role:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRole role) {
        SysUser currentUser = ShiroUtils.getSysUser();
        role.setMerchantId(currentUser.getMerchantId());
        startPage();
        List<SysRole> list = roleService.backSelectRolesByMerchantId(role);
        return getDataTable(list);
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 商户后台管理员新增保存角色
     */
    @RequiresPermissions("back:merchant:role:add")
    @Log(title = "子账户角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysRole role) {
        SysUser currentUser = ShiroUtils.getSysUser();
        role.setCreateBy(currentUser.getLoginName());
        role.setMerchantId(currentUser.getMerchantId());
        role.setAccountType(1);
        role.setRoleKey("merchant:account");
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(roleService.insertRole(role));
    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("back:merchant:role:edit")
    @Log(title = "子账户角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysRole role) {
        role.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 删除角色（物理删除 ）
     *
     * @param ids
     * @return
     */
    @RequiresPermissions("back:merchant:role:remove")
    @Log(title = "子账户角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(roleService.deleteRoleByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }


    /**
     * 分配用户
     */
    @RequiresPermissions("back:merchant:role:user")
    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @PostMapping("/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权
     */
    @Log(title = "子账户角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权
     */
    @Log(title = "子账户角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    public AjaxResult cancelAuthUserAll(Long roleId, String userIds) {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 角色状态修改
     */
    @Log(title = "子账户角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("back:merchant:role:status")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysRole role) {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.changeStatus(role));
    }


}
