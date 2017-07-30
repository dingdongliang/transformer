package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysUserPost;
import com.dyenigma.service.ISysUserPostService;
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
@RequestMapping("/sys/user/post")
public class SysUserPostController extends BaseController{
    @Resource
    private ISysUserPostService sysUserPostService;

    @PostMapping
    public Result add(SysUserPost sysUserPost) {
        sysUserPostService.save(sysUserPost);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysUserPostService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysUserPost sysUserPost) {
        sysUserPostService.update(sysUserPost);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysUserPost sysUserPost = sysUserPostService.findById(id);
        return ResultGenerator.genSuccessResult(sysUserPost);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserPost> list = sysUserPostService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
