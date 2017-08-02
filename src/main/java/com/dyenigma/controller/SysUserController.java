package com.dyenigma.controller;

import com.dyenigma.core.Result;
import com.dyenigma.core.ResultGenerator;
import com.dyenigma.entity.SysCompany;
import com.dyenigma.entity.SysDivision;
import com.dyenigma.entity.SysPost;
import com.dyenigma.entity.SysRole;
import com.dyenigma.entity.SysUser;
import com.dyenigma.entity.SysUserPmsn;
import com.dyenigma.entity.SysUserPost;
import com.dyenigma.entity.SysUserRole;
import com.dyenigma.model.GridModel;
import com.dyenigma.model.TreeModel;
import com.dyenigma.service.ISysCompanyService;
import com.dyenigma.service.ISysDivisionService;
import com.dyenigma.service.ISysPostService;
import com.dyenigma.service.ISysUserPmsnService;
import com.dyenigma.service.ISysUserPostService;
import com.dyenigma.service.ISysUserRoleService;
import com.dyenigma.service.ISysUserService;
import com.dyenigma.util.Constants;
import com.dyenigma.util.PageUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 * author  dyenigma
 * date 2017/07/21
 */
@Controller
@Api(description = "编辑用户API")
@RequestMapping(value = "/manage/users")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysCompanyService sysCompanyService;
    @Resource
    private ISysDivisionService sysDivisionService;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysPostService sysPostService;
    @Resource
    private ISysUserPostService sysUserPostService;
    @Resource
    private ISysUserPmsnService sysUserPmsnService;

    /**
     * Description:获取所有的岗位信息，按树结构显示
     * Name:getPostList
     * Author:Dyenigma
     * Time:2016/4/27 23:17
     * param:[]
     * return:java.util.List<com.dyenigma.model.TreeModel>
     */
    @ResponseBody
    @RequestMapping(value = "/getPostList", produces = "application/json;charset=utf-8")
    public List<TreeModel> getPostList() {
        //获取公司和部门树结构
        List<TreeModel> divList = sysPostService.getCoDivList();
        //循环处理每个部门添加岗位结构,返回
        return sysUserService.getPostList(divList);
    }

    //用户主页面跳转
    @RequestMapping("/usersMain")
    public String main() {
        logger.debug("main() is executed!");
        return "manage/user/userMain";
    }

    /**
     * Title: findAllOrganList
     * Description: 查询所有用户
     * param   request
     * param return 参数
     * return List<Organization> 返回类型
     * throws
     */
    @ResponseBody
    @RequestMapping(value = "/findAllUserList", produces = "application/json;charset=utf-8")
    public List<SysUser> findAllUserList() {
        return sysUserService.findAll();
    }


    @ResponseBody
    @RequestMapping(value = "/findUserByPost/{postId}", produces = "application/json;charset=utf-8")
    public GridModel findUserByPost(@PathVariable("postId") String postId, HttpServletRequest request) {
        logger.debug("findUserByPost() is executed!");
        List<String> idList = sysUserPostService.findByPostId(postId);

        Set<String> idSet = new HashSet<>();

        idSet.addAll(idList.stream().collect(Collectors.toList()));


        return getGridModel(request, idSet);
    }

    @ResponseBody
    @RequestMapping(value = "/findUserByDiv/{divId}", produces = "application/json;charset=utf-8")
    public GridModel findUserByDiv(@PathVariable("divId") String divId, HttpServletRequest request) {
        logger.debug("findUserByDiv() is executed!");
        //先获取部门下属的所有岗位
        List<SysPost> pList = sysPostService.finaPostByDiv(divId);
        //然后逐一获取岗位下的所有用户ID，累加,获取总的用户ID集合
        Set<String> idSet = new HashSet<>();

        for (SysPost post : pList) {
            List<String> perList = sysUserPostService.findByPostId(post.getPostId());
            //一对多，有重复信息，要使用set
            idSet.addAll(perList.stream().collect(Collectors.toList()));
        }

        // 查询，分页展示
        return getGridModel(request, idSet);

    }

    @ResponseBody
    @RequestMapping(value = "/findUserByCo/{coId}", produces = "application/json;charset=utf-8")
    public GridModel findUserByCo(@PathVariable("coId") String coId, HttpServletRequest request) {
        logger.debug("findUserByCo() is executed!");
        //获取所有的下属公司集合
        List<SysCompany> coList = sysCompanyService.AllCoById(coId);
        //循环获取所有公司的下属所有部门
        Set<SysDivision> allDivList = new HashSet<>();
        for (SysCompany co : coList) {
            List<SysDivision> divList = sysDivisionService.findDivByCoId(co.getCoId());
            //一对多，有重复信息，要使用set
            allDivList.addAll(divList.stream().collect(Collectors.toList()));
        }

        //循环获取所有部门下属的所有岗位
        Set<SysPost> allPostList = new HashSet<>();
        for (SysDivision division : allDivList) {
            List<SysPost> pList = sysPostService.finaPostByDiv(division.getDivId());
            //一对多，有重复信息，要使用set
            allPostList.addAll(pList.stream().collect(Collectors.toList()));
        }

        //循环获取每个岗位对应的用户，累加，获取总用户ID集合
        Set<String> totalList = new HashSet<>();
        for (SysPost post : allPostList) {
            List<String> perList = sysUserPostService.findByPostId(post.getPostId());
            //一对多，有重复信息，要使用set
            totalList.addAll(perList.stream().collect(Collectors.toList()));
        }
        //查询，分页展示
        return getGridModel(request, totalList);
    }

    //查询分页显示，适用于findUserByPage方法分页
    private GridModel getGridModel(HttpServletRequest request, Set<String> totalList) {

        int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        GridModel gridModel = new GridModel();
        gridModel.setRows(sysUserService.findUserByPage(pageUtil, totalList));
        //这种获取total的方式还是不严谨，待更换
        gridModel.setTotal(totalList == null ? 0 : totalList.size());
        return gridModel;

    }

    //用户编辑页面跳转
    @RequestMapping("/usersEditDlg")
    public String usersEditDlg() {
        logger.debug("usersEditDlg() is executed!");
        return "manage/user/userEdit";
    }

    //用户岗位设置页面跳转
    @RequestMapping("/userPost")
    public String userPost() {
        logger.debug("userPost() is executed!");
        return "manage/user/userPost";
    }

    //用户角色设置页面跳转
    @RequestMapping("/userRole")
    public String userRole() {
        logger.debug("userRole() is executed!");
        return "manage/user/userRole";
    }

    //用户权限设置页面跳转
    @RequestMapping("/userPmsn")
    public String userPmsn() {
        logger.debug("userPmsn() is executed!");
        return "manage/user/userPmsn";
    }

    /**
     * 搜索所有的用户信息，分页
     * request
     */
    @ResponseBody
    @RequestMapping(value = "/allUserByPage", produces = "application/json;charset=utf-8")
    public GridModel allUserByPage(HttpServletRequest request) {
        logger.debug("allUserByPage() is executed!");
        int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        GridModel gridModel = new GridModel();
        gridModel.setRows(sysUserService.allUserByPage(pageUtil));
        gridModel.setTotal(sysUserService.getCount(null));
        return gridModel;
    }


    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: saveOrUpdateUser
     * Description: 新增用户或者更新用户处理
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateUser", produces = "application/json;charset=utf-8")
    public Result saveOrUpdateUser(SysUser user) {
        if (sysUserService.persistenceUser(user)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/delUser", produces = "application/json;charset=utf-8")
    public Result delUser(HttpServletRequest request) {
        String id = request.getParameter("userId");

        if (sysUserService.delUser(id)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.IS_EXT_SUBMENU);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/usersRoleList", produces = "application/json;charset=utf-8")
    public List<SysRole> usersRoleList(HttpServletRequest request) {
        String id = request.getParameter("userId");

        return sysUserRoleService.findAllByUserId(id);
    }

    /**
     * 保存某个用户的角色分配
     * param request
     * return
     */
    @ResponseBody
    @RequestMapping(value = "/saveUserRoles", produces = "application/json;charset=utf-8")
    public Result saveUserRoles(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String checkedIds = request.getParameter("isCheckedIds");

        if (sysUserRoleService.saveRole(userId, checkedIds)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }

    }

    /**
     * 保存某个用户的岗位分配
     * param request
     * return
     */
    @ResponseBody
    @RequestMapping(value = "/saveUserPost", produces = "application/json;charset=utf-8")
    public Result saveUserPost(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String checkedIds = request.getParameter("isCheckedIds");

        if (sysUserPostService.saveUserPost(userId, checkedIds)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }

    }


    /**
     * 保存某个用户的权限分配
     * param request
     * return
     */
    @ResponseBody
    @RequestMapping(value = "/saveUserPmsn", produces = "application/json;charset=utf-8")
    public Result saveUserPmsn(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String checkedIds = request.getParameter("isCheckedIds");

        if (sysUserPmsnService.saveUserPmsn(userId, checkedIds)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.POST_DATA_FAIL);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUserPostByUsedId", produces = "application/json;charset=utf-8")
    public List<SysUserPost> getUserPostByUsedId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        return sysUserPostService.findByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserRoleByUsedId", produces = "application/json;charset=utf-8")
    public List<SysUserRole> getUserRoleByUsedId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        return sysUserRoleService.findByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserPmsnByUsedId", produces = "application/json;charset=utf-8")
    public List<SysUserPmsn> getUserPmsnByUsedId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        return sysUserPmsnService.findByUserId(userId);
    }

    @ResponseBody
    @PostMapping
    public Result add(SysUser sysUser) {
        sysUserService.save(sysUser);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @PutMapping
    public Result update(SysUser sysUser) {
        sysUserService.update(sysUser);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysUser sysUser = sysUserService.findById(id);
        return ResultGenerator.genSuccessResult(sysUser);
    }

    @ResponseBody
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
