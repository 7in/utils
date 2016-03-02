package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 
 * @author copy
 */
public class SortPlanDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	private String name;

	private String status;

	private String userIds;

	private String executeSpeed;

	private String dataSource;

	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getExecuteSpeed() {
		return executeSpeed;
	}

	public void setExecuteSpeed(String executeSpeed) {
		this.executeSpeed = executeSpeed;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
