package com.dyenigma.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "gen_answer")
public class GenAnswer extends BaseDomain {
    /**
     * 问题ID
     */
    @Id
    @Column(name = "QSTN_ID")
    private String qstnId;

    /**
     * 答复ID
     */
    @Column(name = "ANSR_ID")
    private String ansrId;

    /**
     * 回答内容描述
     */
    @Column(name = "ANSR_DESC")
    private String ansrDesc;

    /**
     * 赞同次数
     */
    @Column(name = "ANSR_AGREE")
    private Integer ansrAgree;

    /**
     * 反对次数
     */
    @Column(name = "ANSR_OPOS")
    private Integer ansrOpos;

    /**
     * 回答日期
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * 修改日期
     */
    @Column(name = "LASTMOD")
    private Date lastmod;

    /**
     * 回答人
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
     * 获取答复ID
     *
     * @return ANSR_ID - 答复ID
     */
    public String getAnsrId() {
        return ansrId;
    }

    /**
     * 设置答复ID
     *
     * @param ansrId 答复ID
     */
    public void setAnsrId(String ansrId) {
        this.ansrId = ansrId;
    }

    /**
     * 获取回答内容描述
     *
     * @return ANSR_DESC - 回答内容描述
     */
    public String getAnsrDesc() {
        return ansrDesc;
    }

    /**
     * 设置回答内容描述
     *
     * @param ansrDesc 回答内容描述
     */
    public void setAnsrDesc(String ansrDesc) {
        this.ansrDesc = ansrDesc;
    }

    /**
     * 获取赞同次数
     *
     * @return ANSR_AGREE - 赞同次数
     */
    public Integer getAnsrAgree() {
        return ansrAgree;
    }

    /**
     * 设置赞同次数
     *
     * @param ansrAgree 赞同次数
     */
    public void setAnsrAgree(Integer ansrAgree) {
        this.ansrAgree = ansrAgree;
    }

    /**
     * 获取反对次数
     *
     * @return ANSR_OPOS - 反对次数
     */
    public Integer getAnsrOpos() {
        return ansrOpos;
    }

    /**
     * 设置反对次数
     *
     * @param ansrOpos 反对次数
     */
    public void setAnsrOpos(Integer ansrOpos) {
        this.ansrOpos = ansrOpos;
    }

    /**
     * 获取回答日期
     *
     * @return CREATED - 回答日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置回答日期
     *
     * @param created 回答日期
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
     * 获取回答人
     *
     * @return CREATER - 回答人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置回答人
     *
     * @param creater 回答人
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