package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.GenAnswer;
import com.dyenigma.service.IGenAnswerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 207/21
 */
@RestController
@Api(description = "问答功能API")
@RequestMapping("/gen/answer")
public class GenAnswerController {
    private final Logger logger = LoggerFactory.getLogger(GenAnswerController.class);
    @Resource
    private IGenAnswerService genAnswerService;

    @PostMapping
    public Result add(GenAnswer genAnswer) {
        genAnswerService.save(genAnswer);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        genAnswerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GenAnswer genAnswer) {
        genAnswerService.update(genAnswer);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        GenAnswer genAnswer = genAnswerService.findById(id);
        return ResultGenerator.genSuccessResult(genAnswer);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GenAnswer> list = genAnswerService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
