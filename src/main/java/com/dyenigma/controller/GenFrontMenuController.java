package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.GenFrontMenu;
import com.dyenigma.service.IGenFrontMenuService;
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
@RequestMapping("/gen/front/menu")
public class GenFrontMenuController extends BaseController{
    @Resource
    private IGenFrontMenuService genFrontMenuService;

    @PostMapping
    public Result add(GenFrontMenu genFrontMenu) {
        genFrontMenuService.save(genFrontMenu);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        genFrontMenuService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GenFrontMenu genFrontMenu) {
        genFrontMenuService.update(genFrontMenu);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        GenFrontMenu genFrontMenu = genFrontMenuService.findById(id);
        return ResultGenerator.genSuccessResult(genFrontMenu);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GenFrontMenu> list = genFrontMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
