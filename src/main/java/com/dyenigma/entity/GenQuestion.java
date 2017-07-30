package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "gen_question")
public class GenQuestion extends BaseDomain  {
    /**
     * 问题ID
     */
    @Id
    @Column(name = "QSTN_ID")
    private String qstnId;

    /**
     * 问题标题
     */
    @Column(name = "QSTN_TITLE")
    private String qstnTitle;

    /**
     * 问题关键字
     */
    @Column(name = "QSTN_KEY")
    private String qstnKey;

    /**
     * 问题描述
     */
    @Column(name = "QSTN_DESC")
    private String qstnDesc;

    /**
     * 当前状态,N:new新问题,C:closed已完结,D:doing进行中,B:back撤回
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 提问日期
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * 修改日期
     */
    @Column(name = "LASTMOD")
    private Date lastmod;

    /**
     * 问题录入员
     */
    @Column(name = "CREATER")
    private String creater;

    /**
     * 修改人
     */
    @Column(name = "MODIFYER")
    private String modifyer;

    /**
     * 获取问题ID
     *
     * @return QSTN_ID - 问题ID
     */
    public String getQstnId() {
        return qstnId;
    }

    /**
     * 设置问题ID
     *
     * @param qstnId 问题ID
     */
    public void setQstnId(String qstnId) {
        this.qstnId = qstnId;
    }

    /**
     * 获取问题标题
     *
     * @return QSTN_TITLE - 问题标题
     */
    public String getQstnTitle() {
        return qstnTitle;
    }

    /**
     * 设置问题标题
     *
     * @param qstnTitle 问题标题
     */
    public void setQstnTitle(String qstnTitle) {
        this.qstnTitle = qstnTitle;
    }

    /**
     * 获取问题关键字
     *
     * @return QSTN_KEY - 问题关键字
     */
    public String getQstnKey() {
        return qstnKey;
    }

    /**
     * 设置问题关键字
     *
     * @param qstnKey 问题关键字
     */
    public void setQstnKey(String qstnKey) {
        this.qstnKey = qstnKey;
    }

    /**
     * 获取问题描述
     *
     * @return QSTN_DESC - 问题描述
     */
    public String getQstnDesc() {
        return qstnDesc;
    }

    /**
     * 设置问题描述
     *
     * @param qstnDesc 问题描述
     */
    public void setQstnDesc(String qstnDesc) {
        this.qstnDesc = qstnDesc;
    }

    /**
     * 获取当前状态,N:new新问题,C:closed已完结,D:doing进行中,B:back撤回
     *
     * @return STATUS - 当前状态,N:new新问题,C:closed已完结,D:doing进行中,B:back撤回
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置当前状态,N:new新问题,C:closed已完结,D:doing进行中,B:back撤回
     *
     * @param status 当前状态,N:new新问题,C:closed已完结,D:doing进行中,B:back撤回
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取提问日期
     *
     * @return CREATED - 提问日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置提问日期
     *
     * @param created 提问日期
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
     * 获取问题录入员
     *
     * @return CREATER - 问题录入员
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置问题录入员
     *
     * @param creater 问题录入员
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