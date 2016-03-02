package com.cainiao.solo.dao;

import com.cainiao.solo.entity.RuleItemDO;

import java.util.List;

/**
 * 规则项
 * 
 * @author copy
 * @date 2012-12-21 下午05:20:00
 * @version 2.0
 * @since 2.0
 */
public interface RuleItemDAO extends DAO<RuleItemDO> {

	/**
	 * 查询使用该RuleUnitCode的所有规则流的Id
	 * 
	 * @param ruleUnitCode
	 * @return
	 */
	public  List<Long> queryRuleFlowId(String ruleUnitCode);
}
