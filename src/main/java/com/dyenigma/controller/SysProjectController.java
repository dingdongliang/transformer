package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPrjRole;
import com.dyenigma.entity.SysProject;
import com.dyenigma.entity.SysUser;
import com.dyenigma.service.ISysPrjRoleService;
import com.dyenigma.service.ISysPrjUserService;
import com.dyenigma.service.ISysProjectService;
import com.dyenigma.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Controller
@Api(description = "编辑项目组API")
@RequestMapping(value = "/manage/project")
public class SysProjectController  {

    private final Logger logger = LoggerFactory.getLogger(SysProjectController.class);

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
    @ResponseBody
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
    public Result saveOrUpdatePrj(SysProject prj) {

        if (sysProjectService.persistencePrj(prj)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }
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
    public Result delPrj(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");

        if (sysProjectService.delPrj(prjId)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.IS_EXT_SUBMENU);
        }
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
    public Result savePrjRoles(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        String checkedIds = request.getParameter("isCheckedIds");

        if (sysProjectService.savePrjRole(prjId, checkedIds)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }
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
    public Result savePrjUsers(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        String checkedIds = request.getParameter("isCheckedIds");

        if (sysProjectService.savePrjUsers(prjId, checkedIds)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }

    }

    @RequestMapping(value = "/searchPrjUser", produces = "application/json;charset=utf-8")
    public List<SysUser> searchPrjUser(HttpServletRequest request) {
        String prjId = request.getParameter("prjId");
        return sysProjectService.searchPrjUser(prjId);
    }

    /**
     * param    request
     * param return 参数
     * return
     * throws
     * Title: organEditDlg
     * Description: 跳转到编辑组织页面
     */
    @RequestMapping(value = "/toSetRole", method = RequestMethod.GET)
    public String toSetRole() {

        logger.debug("toSetRole() is executed!");

        return "manage/project/prjRole";
    }

    /**
     * param    request
     * param return 参数
     * return
     * throws
     * Title: organEditDlg
     * Description: 跳转到编辑组织页面
     */
    @RequestMapping(value = "/toUserList", method = RequestMethod.GET)
    public String toUserList() {

        logger.debug("toUserList() is executed!");

        return "manage/project/prjUser";
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
