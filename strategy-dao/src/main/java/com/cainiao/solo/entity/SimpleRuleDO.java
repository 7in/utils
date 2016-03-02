package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

import java.util.Date;

/**
 * 简单规则
 * 
 * @author copy
 * @date 2012-12-19 下午02:39:19
 * @version 2.0
 * @since 2.0
 */
public class SimpleRuleDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	/**
	 * 对应规则流的id
	 */
	private Long ruleFlowId;

	/**
	 * 对应规则的执行优先级
	 */
	private Integer priority;

	/**
	 * 用户自定义文案提醒
	 */
	private String msg;

	/**
	 * 生效时间
	 */
	private Date effectiveTime;

	/**
	 * 失效时间
	 */
	private Date failureTime;

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Date getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}

	public Long getRuleFlowId() {
		return ruleFlowId;
	}

	public void setRuleFlowId(Long ruleFlowId) {
		this.ruleFlowId = ruleFlowId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
