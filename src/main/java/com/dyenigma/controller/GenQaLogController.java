package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.GenQaLog;
import com.dyenigma.service.IGenQaLogService;
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
@RequestMapping("/gen/qa/log")
public class GenQaLogController extends BaseController{
    @Resource
    private IGenQaLogService genQaLogService;

    @PostMapping
    public Result add(GenQaLog genQaLog) {
        genQaLogService.save(genQaLog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        genQaLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GenQaLog genQaLog) {
        genQaLogService.update(genQaLog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        GenQaLog genQaLog = genQaLogService.findById(id);
        return ResultGenerator.genSuccessResult(genQaLog);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GenQaLog> list = genQaLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
