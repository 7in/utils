package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 
 * @author copy
 */
public class SortPlanDetailDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	private Long sortPlanId;

	private Long userId;

	private String userName;

	private String operation;

	private String note;

	public Long getSortPlanId() {
		return sortPlanId;
	}

	public void setSortPlanId(Long sortPlanId) {
		this.sortPlanId = sortPlanId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
