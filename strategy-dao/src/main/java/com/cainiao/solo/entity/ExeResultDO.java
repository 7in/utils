package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 
 * @Description: 调用日志的DO
 * 
 * @author copy
 * 
 * @date 2013-1-29 下午2:59:32
 * 
 */
public class ExeResultDO extends BaseDOExt {

	private static final long serialVersionUID = -654460469458229774L;

	private String runningMan;

	private String msg;

	private Integer status;

	private String bizCat;

    private Long group;

    private String env;

    private String attribute;

    public String getRunningMan() {
        return runningMan;
    }

    public void setRunningMan(String runningMan) {
        this.runningMan = runningMan;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBizCat() {
        return bizCat;
    }

    public void setBizCat(String bizCat) {
        this.bizCat = bizCat;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
