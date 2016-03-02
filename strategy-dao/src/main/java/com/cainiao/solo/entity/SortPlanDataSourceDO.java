package com.cainiao.solo.entity;

import com.cainiao.solo.base.BaseDOExt;

/**
 * 
 * @author copy
 */
public class SortPlanDataSourceDO extends BaseDOExt {

	private static final long serialVersionUID = 1L;

	private Long sortPlanId;

	private Long userId;

	private String userName;

	private Long itemId;

	private String itemName;

	private String extParameter;

	private Integer level;

	private String result;

	private String resultMsg;

	private String tableIndex;

	private String tmpResult;

	public String getTmpResult() {
		return tmpResult;
	}

	public void setTmpResult(String tmpResult) {
		this.tmpResult = tmpResult;
	}

	/**
	 * group by 查询的时候，用来传递返回值
	 */
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}

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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getExtParameter() {
		return extParameter;
	}

	public void setExtParameter(String extParameter) {
		this.extParameter = extParameter;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}
