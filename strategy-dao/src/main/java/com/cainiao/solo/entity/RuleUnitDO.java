package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 规则单元
 * 
 * @author copy
 * @date 2012-12-19 下午02:38:03
 * @version 2.0
 * @since 2.0
 */
public class RuleUnitDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	/**
	 * 对应表单处理器，执行器，事实组装器的。
	 */
	private String code;

	/**
	 * 0为关闭，1为打开。打开时，才进行验证，关闭时，所有验证都默认通过。
	 */
	private Integer switchFlag;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 0表示为condition，1表示为action，3表示为both。
	 */
	private Integer type;

	/**
	 * 使用这个规则单元的必要参数
	 */
	private String parameter;

	/**
	 * 文案提醒
	 */
	private String msg;

	/**
	 * 对应维度的id
	 */
	private Long dimensionId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}

	public Integer getSwitchFlag() {
		return switchFlag;
	}

	public void setSwitchFlag(Integer switchFlag) {
		this.switchFlag = switchFlag;
	}

}
