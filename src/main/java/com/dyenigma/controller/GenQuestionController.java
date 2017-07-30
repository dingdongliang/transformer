package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.GenQuestion;
import com.dyenigma.model.GridModel;
import com.dyenigma.service.IGenQuestionService;
import com.dyenigma.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@RestController
@RequestMapping(value = "/manage/qstn")
public class GenQuestionController extends BaseController {


    @Resource
    private IGenQuestionService genQuestionService;

    /**
     * param return 参数
     * return String 返回类型
     * throws
     * Title: main
     * Description: 打开操作菜单页面
     */
    @RequestMapping("/qaList")
    public String qaList() {

        logger.debug("qaList() is executed!");

        return "manage/research/qaList";
    }

    @ResponseBody
    @RequestMapping(value = "/findAllByPage", produces = "application/json;charset=utf-8")
    public GridModel findAllByPage(HttpServletRequest request) {
        logger.debug("findAllByPage() is executed!");
        int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        GridModel gridModel = new GridModel();
        gridModel.setRows(genQuestionService.findAllByPage(pageUtil));
        gridModel.setTotal(genQuestionService.getCount(null));
        return gridModel;
    }

    @PostMapping
    public Result add(GenQuestion genQuestion) {
        genQuestionService.save(genQuestion);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        genQuestionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GenQuestion genQuestion) {
        genQuestionService.update(genQuestion);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        GenQuestion genQuestion = genQuestionService.findById(id);
        return ResultGenerator.genSuccessResult(genQuestion);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GenQuestion> list = genQuestionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
