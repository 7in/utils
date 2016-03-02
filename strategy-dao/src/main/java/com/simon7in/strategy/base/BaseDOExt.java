package com.simon7in.strategy.base;



/**
 * 
 * 
 * @author copy
 * @date 2013-1-9 下午01:55:33
 * @version 2.0
 * @since 2.0
 */
public class BaseDOExt extends BaseDO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否删除
	 */
	private Integer isDeleted = new Integer(0);

	private Long id;

	private Integer startRow;

	private Integer pageSize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
