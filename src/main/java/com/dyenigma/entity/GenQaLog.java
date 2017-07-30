package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "gen_qa_log")
public class GenQaLog extends BaseDomain {
    /**
     * 日志ID
     */
    @Id
    @Column(name = "LOG_ID")
    private String logId;

    /**
     * 操作类型，A:agree赞同,O:Oppose反对
     */
    @Column(name = "QA_TYPE")
    private String qaType;

    /**
     * 操作日期
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * 修改日期
     */
    @Column(name = "LASTMOD")
    private Date lastmod;

    /**
     * 操作人
     */
    @Column(name = "CREATER")
    private String creater;

    /**
     * 修改人
     */
    @Column(name = "MODIFYER")
    private String modifyer;

    /**
     * 获取日志ID
     *
     * @return LOG_ID - 日志ID
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置日志ID
     *
     * @param logId 日志ID
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * 获取操作类型，A:agree赞同,O:Oppose反对
     *
     * @return QA_TYPE - 操作类型，A:agree赞同,O:Oppose反对
     */
    public String getQaType() {
        return qaType;
    }

    /**
     * 设置操作类型，A:agree赞同,O:Oppose反对
     *
     * @param qaType 操作类型，A:agree赞同,O:Oppose反对
     */
    public void setQaType(String qaType) {
        this.qaType = qaType;
    }

    /**
     * 获取操作日期
     *
     * @return CREATED - 操作日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置操作日期
     *
     * @param created 操作日期
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
     * 获取操作人
     *
     * @return CREATER - 操作人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置操作人
     *
     * @param creater 操作人
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