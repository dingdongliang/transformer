package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPrjUser;
import com.dyenigma.service.ISysPrjUserService;
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
@RequestMapping("/sys/prj/user")
public class SysPrjUserController extends BaseController{
    @Resource
    private ISysPrjUserService sysPrjUserService;

    @PostMapping
    public Result add(SysPrjUser sysPrjUser) {
        sysPrjUserService.save(sysPrjUser);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysPrjUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysPrjUser sysPrjUser) {
        sysPrjUserService.update(sysPrjUser);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysPrjUser sysPrjUser = sysPrjUserService.findById(id);
        return ResultGenerator.genSuccessResult(sysPrjUser);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysPrjUser> list = sysPrjUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
