package com.dyenigma.controller;

import com.alibaba.fastjson.JSONArray;
import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPermission;
import com.dyenigma.model.Json;
import com.dyenigma.model.MultiMenu;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysPermissionService;
import com.dyenigma.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Controller
@Api(description = "权限管理API")
@RequestMapping(value = "/manage/menu")
public class SysPermissionController extends BaseController {

    @Resource
    private ISysPermissionService sysPermissionService;

    /**
     * param return 参数
     * return String 返回类型
     * throws
     * Title: main
     * Description: 打开操作菜单页面
     */
    @RequestMapping("/menuMain")
    public String main() {

        logger.debug("main() is executed!");

        return "manage/permission/pmsnMain";
    }

    /**
     * param    request
     * param return 参数
     * return List<SysPermission> 返回类型
     * throws
     * Title: findAllFunctionList
     * Description: 显示所有可操作的菜单项，用于菜单编辑页面
     */
    @ResponseBody
    @RequestMapping(value = "/findAllMenuList", produces = "application/json;charset=utf-8")
    public List<SysPermission> findAllMenuList(HttpServletRequest request) {
        String pmsnId = request.getParameter("id");
        return sysPermissionService.findByPid(pmsnId);
    }


    /**
     * 用于角色权限菜单分配
     */
    @ResponseBody
    @RequestMapping(value = "/findAllRoleMenu", produces = "application/json;charset=utf-8")
    public List<MultiMenu> findAllRoleMenu() {
        return sysPermissionService.multiMenu();
    }


    /**
     * param return 参数
     * return List<TreeModel> 返回类型
     * throws
     * Title: findSuperFunc
     * Description:显示所有可添加子项的菜单项
     */
    @ResponseBody
    @RequestMapping(value = "/findSuperMenu", produces = "application/json;charset=utf-8")
    public List<TreeModel> findSuperFunc() {
        return sysPermissionService.findSuperFunc();
    }


    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: delFunction
     * Description: 删除菜单处理
     */
    @ResponseBody
    @RequestMapping(value = "/delMenu", produces = "application/json;charset=utf-8")
    public String delFunction(HttpServletRequest request) {
        String id = request.getParameter("id");

        Json json = new Json();
        if (sysPermissionService.deleteById(id)) {
            json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
        }
        return JSONArray.toJSONString(json);
    }

    /**
     * param    request
     * param return 参数
     * return ModelAndView 返回类型
     * throws
     * Title: functionEditDlg
     * Description: 跳转到编辑菜单页面
     */
    @RequestMapping(value = "/menuEditDlg", method = RequestMethod.GET)
    public ModelAndView functionEditDlg() {

        logger.debug("functionEditDlg() is executed!");

        ModelAndView model = new ModelAndView();
        model.setViewName("/manage/permission/pmsnEdit");

        return model;
    }

    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: saveOrUpdateFunc
     * Description: 新增菜单或者更新菜单处理
     */
    @RequiresPermissions({"menuAdd", "menuEdit"})
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateMenu", produces = "application/json;charset=utf-8")
    public String saveOrUpdateFunc(SysPermission permission) {
        Json json = getMessage(sysPermissionService.persistenceFunction(permission));
        return JSONArray.toJSONString(json);
    }

    @PostMapping
    public Result add(SysPermission sysPermission) {
        sysPermissionService.save(sysPermission);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysPermissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysPermission sysPermission) {
        sysPermissionService.update(sysPermission);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysPermission sysPermission = sysPermissionService.findById(id);
        return ResultGenerator.genSuccessResult(sysPermission);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysPermission> list = sysPermissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
