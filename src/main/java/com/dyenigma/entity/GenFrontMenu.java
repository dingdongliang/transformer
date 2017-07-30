package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "gen_front_menu")
public class GenFrontMenu extends BaseDomain {
    /**
     * 菜单ID
     */
    @Id
    @Column(name = "FM_ID")
    private String fmId;

    /**
     * 菜单链接
     */
    @Column(name = "FM_URL")
    private String fmUrl;

    /**
     * 菜单描述
     */
    @Column(name = "FM_DESC")
    private String fmDesc;

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
     * 录入日期
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * 修改日期
     */
    @Column(name = "LASTMOD")
    private Date lastmod;

    /**
     * 录入人
     */
    @Column(name = "CREATER")
    private String creater;

    /**
     * 修改人
     */
    @Column(name = "MODIFYER")
    private String modifyer;

    /**
     * 获取菜单ID
     *
     * @return FM_ID - 菜单ID
     */
    public String getFmId() {
        return fmId;
    }

    /**
     * 设置菜单ID
     *
     * @param fmId 菜单ID
     */
    public void setFmId(String fmId) {
        this.fmId = fmId;
    }

    /**
     * 获取菜单链接
     *
     * @return FM_URL - 菜单链接
     */
    public String getFmUrl() {
        return fmUrl;
    }

    /**
     * 设置菜单链接
     *
     * @param fmUrl 菜单链接
     */
    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }

    /**
     * 获取菜单描述
     *
     * @return FM_DESC - 菜单描述
     */
    public String getFmDesc() {
        return fmDesc;
    }

    /**
     * 设置菜单描述
     *
     * @param fmDesc 菜单描述
     */
    public void setFmDesc(String fmDesc) {
        this.fmDesc = fmDesc;
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
     * 获取录入日期
     *
     * @return CREATED - 录入日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置录入日期
     *
     * @param created 录入日期
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
     * 获取录入人
     *
     * @return CREATER - 录入人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置录入人
     *
     * @param creater 录入人
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