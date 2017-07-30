package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPostRole;
import com.dyenigma.service.ISysPostRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Description:
* author  dyenigma
* date 2017/07/21
*/
@RestController
@RequestMapping("/sys/post/role")
public class SysPostRoleController extends BaseController {
    @Resource
    private ISysPostRoleService sysPostRoleService;

    @PostMapping
    public Result add(SysPostRole sysPostRole) {
        sysPostRoleService.save(sysPostRole);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysPostRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysPostRole sysPostRole) {
        sysPostRoleService.update(sysPostRole);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysPostRole sysPostRole = sysPostRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysPostRole);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysPostRole> list = sysPostRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
