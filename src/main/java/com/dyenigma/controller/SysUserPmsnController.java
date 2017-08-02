package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysUserPmsn;
import com.dyenigma.service.ISysUserPmsnService;
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
 * date 2017/07/21
 */
@RestController
@Api(description = "用户权限分配API")
@RequestMapping("/sys/user/pmsn")
public class SysUserPmsnController {
    private final Logger logger = LoggerFactory.getLogger(SysUserPmsnController.class);

    @Resource
    private ISysUserPmsnService sysUserPmsnService;

    @PostMapping
    public Result add(SysUserPmsn sysUserPmsn) {
        sysUserPmsnService.save(sysUserPmsn);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysUserPmsnService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysUserPmsn sysUserPmsn) {
        sysUserPmsnService.update(sysUserPmsn);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysUserPmsn sysUserPmsn = sysUserPmsnService.findById(id);
        return ResultGenerator.genSuccessResult(sysUserPmsn);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserPmsn> list = sysUserPmsnService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
