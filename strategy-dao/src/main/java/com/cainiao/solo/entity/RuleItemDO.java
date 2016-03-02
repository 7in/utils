package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 规则项
 * 
 * @author copy
 * @date 2012-12-19 下午02:32:49
 * @version 2.0
 * @since 2.0
 */
public class RuleItemDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	/**
	 * 类型，0表示为condition,1表示为action
	 */
	private Integer type;

	/**
	 * 比较操作：包含：<;<=;=;>=;>;is;notIs;in;notIn;other;
	 */
	private String compareType;

	/**
	 * 多值的情况下，用";"号隔开。
	 */
	private String value;

	/**
	 * 对应简单规则的id
	 */
	private Long simpleRuleId;

	/**
	 * 对应规则流的id
	 */
	private Long ruleFlowId;

	/**
	 * 对应规则单元的唯一标识符
	 */
	private String ruleUnitCode;

	/**
	 * 拓展字段
	 */
	private String attribute;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getSimpleRuleId() {
		return simpleRuleId;
	}

	public void setSimpleRuleId(Long simpleRuleId) {
		this.simpleRuleId = simpleRuleId;
	}

	public String getRuleUnitCode() {
		return ruleUnitCode;
	}

	public void setRuleUnitCode(String ruleUnitCode) {
		this.ruleUnitCode = ruleUnitCode;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getRuleFlowId() {
		return ruleFlowId;
	}

	public void setRuleFlowId(Long ruleFlowId) {
		this.ruleFlowId = ruleFlowId;
	}

	public String getCompareType() {
		return compareType;
	}

	public void setCompareType(String compareType) {
		this.compareType = compareType;
	}

}
