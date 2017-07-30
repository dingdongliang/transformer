package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user_pmsn")
public class SysUserPmsn extends BaseDomain {
    /**
     * 用户权限配置自增ID
     */
    @Id
    @Column(name = "UPM_ID")
    private String upmId;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 权限ID
     */
    @Column(name = "PMSN_ID")
    private String pmsnId;

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
     * 获取用户权限配置自增ID
     *
     * @return UPM_ID - 用户权限配置自增ID
     */
    public String getUpmId() {
        return upmId;
    }

    /**
     * 设置用户权限配置自增ID
     *
     * @param upmId 用户权限配置自增ID
     */
    public void setUpmId(String upmId) {
        this.upmId = upmId;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取权限ID
     *
     * @return PMSN_ID - 权限ID
     */
    public String getPmsnId() {
        return pmsnId;
    }

    /**
     * 设置权限ID
     *
     * @param pmsnId 权限ID
     */
    public void setPmsnId(String pmsnId) {
        this.pmsnId = pmsnId;
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