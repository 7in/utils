package com.simon7in.strategy.entity;

import com.simon7in.strategy.base.BaseDOExt;

/**
 * Created by jiayu.shenjy on 2016/1/26.
 */
public class PlaybackRecordDO extends BaseDOExt {

    private static final long serialVersionUID = 1L;

    /**
     * 行业
     */
    String industry;
    /**
     * 执行状态
     */
    Integer status;
    /**
     * 执行人
     */
    String staff;
    /**
     *备注
     */
    String msg;
    /**
     *备注
     */
    String attribute;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
