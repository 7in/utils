/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: wlb-client
 * 
 * BaseDTO.java File Created at 下午07:15:21
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
package com.simon7in.strategy.to;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author guichen 2011-1-19
 * @author yihui 2011-1-24 增加属性和getPartnerCode方法
 */
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = -2032404835989215210L;

	/** 请求方业务code */
	protected String outBizCode;
	
	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	/**
	 * 合作伙伴code，如果需要判断，需要在子类中重载此方法
	 * @return
	 */
	public String getPartnerCode() {
		return null;
	}

	public String toString() {
		return  ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
