package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_prj_user")
public class SysPrjUser extends BaseDomain {
    /**
     * 项目成员配置自增ID
     */
    @Id
    @Column(name = "PU_ID")
    private String puId;

    /**
     * 项目组ID
     */
    @Column(name = "PRJ_ID")
    private String prjId;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;

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
     * 获取项目成员配置自增ID
     *
     * @return PU_ID - 项目成员配置自增ID
     */
    public String getPuId() {
        return puId;
    }

    /**
     * 设置项目成员配置自增ID
     *
     * @param puId 项目成员配置自增ID
     */
    public void setPuId(String puId) {
        this.puId = puId;
    }

    /**
     * 获取项目组ID
     *
     * @return PRJ_ID - 项目组ID
     */
    public String getPrjId() {
        return prjId;
    }

    /**
     * 设置项目组ID
     *
     * @param prjId 项目组ID
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId;
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