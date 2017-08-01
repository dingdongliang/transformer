package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysUserRole;
import com.dyenigma.service.ISysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Controller
@Api(description = "用户角色分配API")
@RequestMapping(value = "/manage/userRole")
public class SysUserRoleController  {

    private final Logger logger = LoggerFactory.getLogger(SysUserRoleController.class);

    @Resource
    private ISysUserRoleService sysUserRoleService;

    @RequestMapping("/setRole")
    public String setRole() {
        logger.debug("setRole() is executed!");
        return "manage/userRole/userRoleMain";
    }

    @PostMapping
    public Result add(SysUserRole sysUserRole) {
        sysUserRoleService.save(sysUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysUserRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysUserRole sysUserRole) {
        sysUserRoleService.update(sysUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysUserRole sysUserRole = sysUserRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysUserRole);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserRole> list = sysUserRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
