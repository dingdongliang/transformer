package com.dyenigma.controller;

import com.alibaba.fastjson.JSONArray;
import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPrjRole;
import com.dyenigma.entity.SysProject;
import com.dyenigma.entity.SysUser;
import com.dyenigma.model.Json;
import com.dyenigma.service.ISysPrjRoleService;
import com.dyenigma.service.ISysPrjUserService;
import com.dyenigma.service.ISysProjectService;
import com.dyenigma.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@RestController
@RequestMapping(value = "/manage/project")
public class SysProjectController extends BaseController {


    @Resource
    private ISysProjectService sysProjectService;
    @Resource
    private ISysPrjRoleService sysPrjRoleService;
    @Resource
    private ISysPrjUserService sysPrjUserService;

    //项目主页面跳转
    @RequestMapping(value = "/prjMain")
    public String prjMain() {
        logger.debug("prjMain() is executed!");
        return "manage/project/prjMain";
    }

    //项目编辑页面跳转
    @RequestMapping(value = "/prjEdit")
    public String prjEdit() {
        logger.debug("prjEdit() is executed!");
        return "manage/project/prjEdit";
    }

    /**
     * Description: 查询公司内项目组信息
     * Name:getPrjByCoId
     * Author:dyenigma
     * Time:2016/4/29 15:53
     * param:[request]
     * return:java.util.List<com.dyenigma.entity.SysProject>
     */
    @RequestMapping(value = "/getPrjByCoId/{coId}", produces = "application/json;charset=utf-8")
    public List<SysProject> getPrjByCoId(@PathVariable("coId") String coId) {
        return sysProjectService.getPrjByCoId(coId);
    }


    /**
     * Description: 项目组的新增和修改操作
     * Name:saveOrUpdatePrj
     * Author:dyenigma
     * Time:2016/4/29 16:02
     * param:[prj]
     * return:java.lang.String
     */
    @RequestMapping(value = "/saveOrUpdatePrj", produces = "application/json;charset=utf-8")
    public String saveOrUpdatePrj(SysProject prj) {
        Json json = getMessage(sysProjectService.persistencePrj(prj));
        return JSONArray.toJSONString(json);
    }

    /**
     * Description: 把项目组标记为过期、结束
     * Name:delPrj
     * Author:dyenigma
     * Time:2016/4/29 16:06
     * param:[request]
     * return:java.lang.String
     */
    @RequestMapping(value = "/delPrj", produces = "application/json;charset=utf-8")
    public String delPrj(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");

        Json json = new Json();
        boolean flag = sysProjectService.delPrj(prjId);

        if (flag) {
            json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
        }

        return JSONArray.toJSONString(json);
    }

    /**
     * Description: 授予项目组角色
     * Name:prjRole
     * Author:dyenigma
     * Time:2016/4/29 16:09
     * param:[request]
     * return:java.util.List<com.dyenigma.entity.SysProject>
     */
    @RequestMapping(value = "/prjRole", produces = "application/json;charset=utf-8")
    public List<SysProject> prjRole(HttpServletRequest request) {
        String coId = request.getParameter("coId");
        return sysProjectService.getPrjByCoId(coId);
    }


    /**
     * Description: 项目组角色分配
     * Name:savePrjRoles
     * Author:dyenigma
     * Time:2016/4/29 16:21
     * param:[request]
     * return:java.lang.String
     */
    @RequestMapping(value = "/savePrjRoles", produces = "application/json;charset=utf-8")
    public String savePrjRoles(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        String checkedIds = request.getParameter("isCheckedIds");
        Json json = new Json();

        if (sysProjectService.savePrjRole(prjId, checkedIds)) {
            json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL);
        }

        return JSONArray.toJSONString(json);
    }

    /**
     * Description: 项目组角色分配
     * Name:savePrjRoles
     * Author:dyenigma
     * Time:2016/4/29 16:21
     * param:[request]
     * return:java.lang.String
     */
    @RequestMapping(value = "/savePrjUsers", produces = "application/json;charset=utf-8")
    public String savePrjUsers(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        String checkedIds = request.getParameter("isCheckedIds");
        Json json = new Json();

        if (sysProjectService.savePrjUsers(prjId, checkedIds)) {
            json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL);
        }

        return JSONArray.toJSONString(json);
    }

    @RequestMapping(value = "/searchPrjUser", produces = "application/json;charset=utf-8")
    public List<SysUser> searchPrjUser(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        return sysProjectService.searchPrjUser(prjId);
    }

    /**
     * param    request
     * param return 参数
     * return ModelAndView 返回类型
     * throws
     * Title: organEditDlg
     * Description: 跳转到编辑组织页面
     */
    @RequestMapping(value = "/toSetRole", method = RequestMethod.GET)
    public ModelAndView toSetRole() {

        logger.debug("toSetRole() is executed!");

        ModelAndView model = new ModelAndView();
        model.setViewName("manage/project/prjRole");

        return model;
    }

    /**
     * param    request
     * param return 参数
     * return ModelAndView 返回类型
     * throws
     * Title: organEditDlg
     * Description: 跳转到编辑组织页面
     */
    @RequestMapping(value = "/toUserList", method = RequestMethod.GET)
    public ModelAndView toUserList() {

        logger.debug("toUserList() is executed!");

        ModelAndView model = new ModelAndView();
        model.setViewName("manage/project/prjUser");

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/getPrjRoleByPrjId", produces = "application/json;charset=utf-8")
    public List<SysPrjRole> getPrjRoleByPrjId(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        return sysPrjRoleService.getPrjRoleByPrjId(prjId);
    }


    @ResponseBody
    @RequestMapping(value = "/getPrjUserByPrjId", produces = "application/json;charset=utf-8")
    public List<SysUser> getPrjUserByPrjId(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        return sysPrjUserService.getPrjUserByPrjId(prjId);
    }

    @PostMapping
    public Result add(SysProject sysProject) {
        sysProjectService.save(sysProject);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysProjectService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysProject sysProject) {
        sysProjectService.update(sysProject);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysProject sysProject = sysProjectService.findById(id);
        return ResultGenerator.genSuccessResult(sysProject);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysProject> list = sysProjectService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
