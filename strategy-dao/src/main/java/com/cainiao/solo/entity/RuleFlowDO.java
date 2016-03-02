package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 规则流
 * 
 * @author copy
 * @date 2012-12-19 下午02:31:13
 * @version 2.0
 * @since 2.0
 */
public class RuleFlowDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	/**
	 * 规则流名称
	 */
	private String name;

	/**
	 * 规则流唯一标识符
	 */
	private String code;

	/**
	 * 状态：0表示未生效，1表示已生效。
	 */
	private Integer status;

	/**
	 * 对应规则业务方的id
	 */
	private Long ruleBusinessId;

	/**
	 * 是否穿透执行
	 */
	private Integer penetrate;

	/**
	 * 用";"号隔开
	 */
	private String parameter;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 规则表达式
	 */
	private String expression;

	/**
	 * 规则执行优先级
	 */
	private Integer priority;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRuleBusinessId() {
		return ruleBusinessId;
	}

	public void setRuleBusinessId(Long ruleBusinessId) {
		this.ruleBusinessId = ruleBusinessId;
	}

	public Integer getPenetrate() {
		return penetrate;
	}

	public void setPenetrate(Integer penetrate) {
		this.penetrate = penetrate;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * 这个用来处理可以为空的情况
	 * 
	 * @param note
	 */
	public String getNote() {
		if (note == null) {
			note = "";
		}
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 这个用来处理可以为空的情况
	 * 
	 * @return
	 */
	public String getExpression() {
		if (expression == null) {
			expression = "";
		}
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
