/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 *
 * Project: wlb-client
 *
 * Query.java File Created at 下午01:40:49
 *
 *
 * Copyright 2010 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.simon7in.strategy.query;

import org.apache.commons.lang.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guichen 2011-1-23
 */
public class Query<T> extends Pagination implements Serializable {
    private Date startDate;
    
    private Date endDate;
    /**
     *
     */
    private static final long serialVersionUID = 386145622921888459L;

    /**
     * 对象中没有的值 可以放到这里
     * 具体：例如库存表的 types,storeCodes等等
     */
    private Map<String, Object> otherValue = new HashMap<String, Object>();
    
    @NotNull(message = "queryObject is null")
    @Valid
    private T queryObject;

    private String orderField;

    private boolean desc = true;

    public T getQueryObject() {
        return queryObject;
    }

    public void setQueryObject(T queryObject) {
        this.queryObject = queryObject;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderField() {
        if (!StringUtils.isBlank(orderField)) {
        	return orderField;
        } else {
        	return null;
        }
    }
    
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public boolean isDesc() {
        return desc;
    }

    /**
     * @return 升降序排序方式的sql关键字
     */
    public String getDesc() {
    	return desc ? "DESC" : "ASC";
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    /**
     * @deprecated 语义上要将排序的字段名和升降序拆分开
     */
    @Deprecated
    public String getOrderByClause() {
        if (orderField != null && !orderField.equals("")) {
            return orderField + (desc ? " DESC" : " ");
        } else {
            return null;
        }
    }

    public void copy(Query src, Query tgt) {

        tgt.setPageSize(src.getPageSize());
        tgt.setOrderField(src.getOrderField());
        tgt.setPageIndex(src.getPageIndex());

    }

    public Map<String, Object> getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(Map<String, Object> otherValue) {
        this.otherValue = otherValue;
    }

    public void addOtherValue(String key, Object value) {
        if(otherValue==null){
            otherValue = new HashMap<String, Object>();
        }
        otherValue.put(key, value);
    }
}
