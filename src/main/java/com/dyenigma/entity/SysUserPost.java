package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user_post")
public class SysUserPost extends BaseDomain {
    /**
     * 用户岗位配置自增ID
     */
    @Id
    @Column(name = "UP_ID")
    private String upId;

    /**
     * 角色ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 权限ID
     */
    @Column(name = "POST_ID")
    private String postId;

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
     * 获取用户岗位配置自增ID
     *
     * @return UP_ID - 用户岗位配置自增ID
     */
    public String getUpId() {
        return upId;
    }

    /**
     * 设置用户岗位配置自增ID
     *
     * @param upId 用户岗位配置自增ID
     */
    public void setUpId(String upId) {
        this.upId = upId;
    }

    /**
     * 获取角色ID
     *
     * @return USER_ID - 角色ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置角色ID
     *
     * @param userId 角色ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取权限ID
     *
     * @return POST_ID - 权限ID
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置权限ID
     *
     * @param postId 权限ID
     */
    public void setPostId(String postId) {
        this.postId = postId;
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
}