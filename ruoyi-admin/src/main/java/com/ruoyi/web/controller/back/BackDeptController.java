package com.ruoyi.web.controller.back;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/back/merchant/dept")
public class BackDeptController extends BaseController {

    final static String prefix = "merchant/back/dept";

    @Autowired
    private ISysDeptService deptService;


    @RequiresPermissions("back:merchant:dept:view")
    @GetMapping()
    public String dept(ModelMap mmap) {
        SysUser currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isEmpty(currentUser.getMerchantId())) {
            throw new BusinessException("非商户后台用户，不允许操作,请联系平台管理员");
        }
        SysDept sysDept = new SysDept();
        sysDept.setMerchantId(currentUser.getMerchantId());
        sysDept.setAccountType(0);
        SysDept dept = deptService.backSelectDeptById(sysDept);
        if (dept == null) {
            throw new BusinessException("此商户未添加上级管理部门，请联系平台管理员");
        }
        mmap.put("dept", dept);
        return prefix + "/dept";
    }

    @RequiresPermissions("back:merchant:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept) {
        SysUser currentUser = ShiroUtils.getSysUser();
        dept.setMerchantId(currentUser.getMerchantId());
        List<SysDept> deptList = deptService.backSelectDeptListByMerchantId(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "子账户部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("back:merchant:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDept dept) {
        if (dept.getParentId().equals(dept.getDeptId())) {
            return error("新增部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        SysUser currentUser = ShiroUtils.getSysUser();
        dept.setMerchantId(currentUser.getMerchantId());
        dept.setAccountType(1);
        dept.setCreateBy(currentUser.getLoginName());
        return toAjax(deptService.insertDeptSon(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId) {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "子账户部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("back:merchant:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept) {
        if (dept.getParentId().equals(dept.getDeptId())) {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "子账户部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("back:merchant:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId) {
        if (deptService.selectDeptCount(deptId) > 0) {
            return AjaxResult.warn("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return AjaxResult.warn("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept sysDept = new SysDept();
        sysDept.setDeptId(deptId);
        sysDept.setMerchantId(currentUser.getMerchantId());
        mmap.put("dept", deptService.backSelectDeptById(sysDept));
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept sysDept = new SysDept();
        sysDept.setMerchantId(currentUser.getMerchantId());
        List<Ztree> ztrees = deptService.backSelectDeptMerchantTree(sysDept);
        return ztrees;
    }


}
