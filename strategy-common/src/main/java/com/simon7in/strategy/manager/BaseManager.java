package com.simon7in.strategy.manager;

import java.util.List;

/**
 * Manager层基础类
 * 
 * @author copy
 * @date 2012-12-21 下午08:07:47
 * @version 2.0
 * @since 2.0
 */
public interface BaseManager<T> {

	/**
	 * 新增一个对象
	 * 
	 * @param t
	 * @return
	 */
	public long insert(T t)  ;

	/**
	 * 删除一个对象
	 * 
	 * @param t
	 * @return
	 */
	public boolean delete(Long t)  ;

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delete(List<Long> ids)  ;

	/**
	 * 更新一个对象
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t)  ;

	/**
	 * 根据ID查询一个对象
	 * 
	 * @param id
	 * @return
	 */
	public T query(Long id)  ;

	/**
	 * 根据组合查询条件去查询一堆对象
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List<T> query(T parameterObject)  ;
}
