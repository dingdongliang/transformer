package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_role")
public class SysRole extends BaseDomain {
    /**
     * 角色自增ID
     */
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "ROLE_DESC")
    private String roleDesc;

    /**
     * 排序列
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 当前状态,E:有效的,I:无效的
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 创造日期
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * 修改日期
     */
    @Column(name = "LASTMOD")
    private Date lastmod;

    /**
     * 创建人
     */
    @Column(name = "CREATER")
    private String creater;

    /**
     * 修改人
     */
    @Column(name = "MODIFYER")
    private String modifyer;

    /**
     * 是否默认角色
     */
    @Column(name = "IS_DEFAULT")
    private String isDefault;

    /**
     * 获取角色自增ID
     *
     * @return ROLE_ID - 角色自增ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色自增ID
     *
     * @param roleId 角色自增ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return ROLE_DESC - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * 获取排序列
     *
     * @return SORT - 排序列
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序列
     *
     * @param sort 排序列
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取当前状态,E:有效的,I:无效的
     *
     * @return STATUS - 当前状态,E:有效的,I:无效的
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置当前状态,E:有效的,I:无效的
     *
     * @param status 当前状态,E:有效的,I:无效的
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创造日期
     *
     * @return CREATED - 创造日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创造日期
     *
     * @param created 创造日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取修改日期
     *
     * @return LASTMOD - 修改日期
     */
    public Date getLastmod() {
        return lastmod;
    }

    /**
     * 设置修改日期
     *
     * @param lastmod 修改日期
     */
    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    /**
     * 获取创建人
     *
     * @return CREATER - 创建人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置创建人
     *
     * @param creater 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * 获取修改人
     *
     * @return MODIFYER - 修改人
     */
    public String getModifyer() {
        return modifyer;
    }

    /**
     * 设置修改人
     *
     * @param modifyer 修改人
     */
    public void setModifyer(String modifyer) {
        this.modifyer = modifyer;
    }

    /**
     * 获取是否默认角色
     *
     * @return IS_DEFAULT - 是否默认角色
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认角色
     *
     * @param isDefault 是否默认角色
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}