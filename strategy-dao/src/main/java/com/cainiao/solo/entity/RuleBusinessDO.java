package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 规则业务
 * 
 * @author copy
 * @date 2012-12-19 下午02:23:17
 * @version 2.0
 * @since 2.0
 */
public class RuleBusinessDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 规则业务方的唯一标识符
	 */
	private String code;

	/**
	 * condition维度ids，用“;”号隔开。
	 */
	private String conditionDimensions;

	/**
	 * action维度ids，用“;”号隔开。
	 */
	private String actionDimensions;

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

	public String getConditionDimensions() {
		return conditionDimensions;
	}

	public void setConditionDimensions(String conditionDimensions) {
		this.conditionDimensions = conditionDimensions;
	}

	public String getActionDimensions() {
		return actionDimensions;
	}

	public void setActionDimensions(String actionDimensions) {
		this.actionDimensions = actionDimensions;
	}

}
