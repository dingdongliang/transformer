package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPrjRole;
import com.dyenigma.service.ISysPrjRoleService;
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
@RequestMapping("/sys/prj/role")
public class SysPrjRoleController extends BaseController{
    @Resource
    private ISysPrjRoleService sysPrjRoleService;

    @PostMapping
    public Result add(SysPrjRole sysPrjRole) {
        sysPrjRoleService.save(sysPrjRole);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysPrjRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysPrjRole sysPrjRole) {
        sysPrjRoleService.update(sysPrjRole);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysPrjRole sysPrjRole = sysPrjRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysPrjRole);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysPrjRole> list = sysPrjRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
