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
public class ExeDetailDO extends BaseDOExt {

	private static final long serialVersionUID = -654460469458229774L;

	private String name;

	private String data;

	private String code;

	private Long exeId;

	private String msg;

	private Integer result;

    private Long bizId;

    private String attribute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getExeId() {
        return exeId;
    }

    public void setExeId(Long exeId) {
        this.exeId = exeId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
