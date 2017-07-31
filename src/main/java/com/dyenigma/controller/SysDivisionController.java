package com.dyenigma.controller;

import com.alibaba.fastjson.JSONArray;
import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysDivision;
import com.dyenigma.model.Json;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysDivisionService;
import com.dyenigma.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Controller
@Api(description = "组织管理API")
@RequestMapping(value = "/manage/organ")
public class SysDivisionController extends BaseController {

    @Autowired
    private ISysDivisionService sysDivisionService;

    @RequestMapping("/organMain")
    public String main() {
        logger.debug("main() is executed!");
        return "manage/division/divMain";
    }

    @ResponseBody
    @RequestMapping(value = "/findDivByCoId/{coId}", produces = "application/json;charset=utf-8")
    public List<SysDivision> findDivByCoId(@PathVariable("coId") String coId) {
        return sysDivisionService.findDivByCoId(coId);
    }


    /**
     * param    request
     * param return 参数
     * return ModelAndView 返回类型
     * throws
     * Title: organEditDlg
     * Description: 跳转到编辑组织页面
     */
    @RequestMapping(value = "/organEditDlg/{coId}", method = RequestMethod.GET)
    public ModelAndView organEditDlg(@PathVariable("coId") String companyId, HttpServletRequest request) {

        logger.debug("organEditDlg() is executed!");

        request.setAttribute("coId", companyId);

        ModelAndView model = new ModelAndView();
        model.setViewName("manage/division/divEdit");

        return model;
    }

    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: delOrgan
     * Description: 删除组织处理
     */
    @ResponseBody
    @RequestMapping(value = "/delOrgan", produces = "application/json;charset=utf-8")
    public String delOrgan(HttpServletRequest request) {
        String id = request.getParameter("id");

        Json json = new Json();
        boolean flag = sysDivisionService.deleteById(id);

        if (flag) {
            json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
        }

        return JSONArray.toJSONString(json);
    }

    /**
     * param return 参数
     * return List<TreeModel> 返回类型
     * throws
     * Title: findSuperOrgan
     * Description:显示所有可添加子项的组织项
     */
    @ResponseBody
    @RequestMapping(value = "/findSuperOrgan/{coId}", produces = "application/json;charset=utf-8")
    public List<TreeModel> findSuperOrgan(@PathVariable("coId") String companyId) {

        return sysDivisionService.findSuperOrgan(companyId);

    }

    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: saveOrUpdateOrgan
     * Description: 新增组织从或者更新组织处理
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateOrgan", produces = "application/json;charset=utf-8")
    public String saveOrUpdateOrgan(SysDivision division) {
        Json json = getMessage(sysDivisionService.persistenceOrgan(division));
        return JSONArray.toJSONString(json);
    }

    @PostMapping
    public Result add(SysDivision sysDivision) {
        sysDivisionService.save(sysDivision);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysDivisionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysDivision sysDivision) {
        sysDivisionService.update(sysDivision);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysDivision sysDivision = sysDivisionService.findById(id);
        return ResultGenerator.genSuccessResult(sysDivision);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysDivision> list = sysDivisionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
