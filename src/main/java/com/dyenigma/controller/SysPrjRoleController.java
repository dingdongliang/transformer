package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPrjRole;
import com.dyenigma.service.ISysPrjRoleService;
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
@Api(description = "项目组-权限分配API")
@RequestMapping("/sys/prj/role")
public class SysPrjRoleController {

    private final Logger logger = LoggerFactory.getLogger(SysPrjRoleController.class);

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
