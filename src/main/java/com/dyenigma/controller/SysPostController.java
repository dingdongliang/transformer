package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysPost;
import com.dyenigma.entity.SysPostRole;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysPostRoleService;
import com.dyenigma.service.ISysPostService;
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
@Api(description = "岗位管理API")
@RequestMapping(value = "/manage/post")
public class SysPostController  {

    private final Logger logger = LoggerFactory.getLogger(SysPostController.class);

    @Resource
    private ISysPostService sysPostService;
    @Resource
    private ISysPostRoleService sysPostRoleService;

    @RequestMapping("/postMain")
    public String main() {
        logger.debug("main() is executed!");
        return "manage/post/postMain";
    }

    @ResponseBody
    @RequestMapping(value = "/getCoDivList", produces = "application/json;charset=utf-8")
    public List<TreeModel> getCoDivList() {
        return sysPostService.getCoDivList();
    }

    /**
     * Description: 查看每个岗位的用户列表
     * Name:postView
     * Author:dyenigma
     * Time:2016/4/28 14:53
     * param:[]
     * return:java.lang.String
     */
    @RequestMapping("/postView")
    public String postView() {
        logger.debug("postView() is executed!");
        return "manage/post/postView";
    }

    /**
     * param    request
     * param return 参数
     * return List<Organization> 返回类型
     * throws
     * Title: findAllOrganList
     * Description: 按部门查询所有岗位
     */
    @ResponseBody
    @RequestMapping(value = "/findPostByDiv/{divId}", produces = "application/json;charset=utf-8")
    public List<SysPost> findPostByDiv(@PathVariable("divId") String divId) {
        return sysPostService.finaPostByDiv(divId);
    }

    /**
     * param    request
     * param return 参数
     * return ModelAndView 返回类型
     * throws
     * Title: organEditDlg
     * Description: 跳转到编辑组织页面
     */
    @RequestMapping(value = "/postEdit", method = RequestMethod.GET)
    public ModelAndView postEdit() {

        logger.debug("postEdit() is executed!");

        ModelAndView model = new ModelAndView();
        model.setViewName("manage/post/postEdit");

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
    @RequestMapping(value = "/toSetRole", method = RequestMethod.GET)
    public ModelAndView toSetRole() {

        logger.debug("toSetRole() is executed!");

        ModelAndView model = new ModelAndView();
        model.setViewName("manage/post/postRole");

        return model;
    }

    /**
     * Description: 保存岗位关联的角色
     * Name:savePostRole
     * Author:dyenigma
     * Time:2016/4/27 16:30
     * param:[request]
     * return:java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/savePostRole", produces = "application/json;charset=utf-8")
    public Result savePostRole(HttpServletRequest request) {
        String postId = request.getParameter("postId");
        String checkedIds = request.getParameter("allCheck");

        if (sysPostRoleService.savePostRole(postId, checkedIds)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }
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
    @RequestMapping(value = "/delPost", produces = "application/json;charset=utf-8")
    public Result delPost(HttpServletRequest request) {
        String postId = request.getParameter("id");

        if (sysPostService.invalidByPrimaryKey(postId) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.IS_EXT_SUBMENU);
        }

    }

    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: saveOrUpdateOrgan
     * Description: 新增岗位或者更新岗位处理
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdatePost", produces = "application/json;charset=utf-8")
    public Result saveOrUpdatePost(SysPost post) {
        if (sysPostService.persistencePost(post)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getPostRoleByPostId", produces = "application/json;charset=utf-8")
    public List<SysPostRole> getPostRoleByPostId(HttpServletRequest request) {
        String postId = request.getParameter("postId");
        return sysPostRoleService.getPostRoleByPostId(postId);
    }

    @PostMapping
    public Result add(SysPost sysPost) {
        sysPostService.save(sysPost);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysPostService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysPost sysPost) {
        sysPostService.update(sysPost);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysPost sysPost = sysPostService.findById(id);
        return ResultGenerator.genSuccessResult(sysPost);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysPost> list = sysPostService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
