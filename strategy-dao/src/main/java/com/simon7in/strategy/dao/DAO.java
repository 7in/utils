package com.simon7in.strategy.dao;

import com.simon7in.strategy.base.BaseDOExt;

import java.util.List;

/**
 * 基础DAO接口
 * 
 * @author copy
 * @date 2012-12-21 下午05:04:29
 * @version 2.0
 * @since 2.0
 * @param <T>
 */
public abstract interface DAO<T extends BaseDOExt> {

	/**
	 * 新增一个对象
	 * 
	 * @param t
	 * @return
	 */
	public abstract long insert(T t);

	/**
	 * 删除一个对象
	 * 
	 * @param t
	 * @return
	 */
	public abstract int delete(T t);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public abstract int delete(Long id);

	/**
	 * 更新一个对象
	 * 
	 * @param t
	 * @return
	 */
	public abstract int update(T t);

	/**
	 * 根据ID查询一个对象
	 * 
	 * @param id
	 * @return
	 */
	public abstract T queryOneObject(Long id);

	/**
	 * 根据组合查询条件去查询一堆对象(这个方法会查询出已经删除的数据，请注意设置is_deleted的值)
	 * 
	 * @param parameterObject
	 * @return
	 */
	public abstract List<T> queryListObject(Object parameterObject);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public abstract int deleteAll(List<Long> ids);

}
