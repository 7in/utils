package com.cainiao.solo.entity;

import java.io.Serializable;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 规则维度
 * 
 * @author copy
 * @date 2012-12-19 下午02:25:18
 * @version 2.0
 * @since 2.0
 */
public class RuleDimensionDO extends BaseDOExt implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 维度类型，0表示为condition维度，1表示为action维度。
	 */
	private Integer type;

	/**
	 * 用于排序
	 */
	private Integer sort;

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
