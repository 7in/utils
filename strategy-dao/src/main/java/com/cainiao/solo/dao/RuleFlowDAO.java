package com.cainiao.solo.dao;

import com.cainiao.solo.entity.RuleFlowDO;

import java.util.List;

/**
 * 规则流
 *
 * @author copy
 * @date 2012-12-21 下午05:19:25
 * @version 2.0
 * @since 2.0
 */
public interface RuleFlowDAO extends DAO<RuleFlowDO> {
    List<RuleFlowDO> findAll();
}
