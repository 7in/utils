package com.simon7in.strategy.dao;

import com.simon7in.strategy.entity.PlaybackRecordDO;

/**
 * 规则业务方
 * 
 * @author copy
 * @date 2012-12-21 下午05:17:43
 * @version 2.0
 * @since 2.0
 */
public interface PlaybackRecordDAO extends DAO<PlaybackRecordDO> {
    /**
     * 查询记录数
     * @return
     */
    public Long getResultCount(PlaybackRecordDO recordDO);
}
