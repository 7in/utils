package com.simon7in.strategy.util;


import com.simon7in.strategy.query.Query;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询类工具类
 * 
 * @author yihui 2011-1-8 创建
 * @author yihui 2011-4-7 修复 BeanUtilsBean describe方法不支持java.util.Date的bug
 * @version 2011-1-8 闪电项目
 */
public class QueryUtils {
	
	/**
	 * 将com.taobao.pamirs.commons.dao.Query转化为Map
	 * 
	 * @param query
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getQueryMap(Query query){
		Map<String,Object> result = getQueryMap(query.getQueryObject());
		if(query.getPageIndex() >0){
			result.put("limit", query.getPageSize());
			result.put("start", (query.getPageIndex() - 1) * query.getPageSize());
		}else{
           throw new RuntimeException("query.pageIndex必须大于0");
        }
		//result.put("orderByClause", query.getOrderByClause());
		result.put("orderByField", query.getOrderField());
		result.put("orderByType", query.getDesc());
		return result;
	}
	
	/**
	 * 将对象中的属性取出，以属性名和属性值作为键值对返回
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getQueryMap(Object object){
		if(object==null) {
			return new HashMap<String,Object>();
		}
		try{
//		    ConvertUtilsBean convertUtils = new ConvertUtilsBean();
//		    //修复 BeanUtilsBean describe方法不支持java.util.Date的bug
//		    convertUtils.register(new DateConverter(), Date.class);
//		    //转换
//		    BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtils,new PropertyUtilsBean());
			//修复 BeanUtilsBean describe方法不支持java.util.Date的bug
			//使用自己的BeanUtilsBean

			return BeanUtilsBean.getInstance().describe(object);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
    public static Map<String, String> getQueryMapStr(Object object) {
        Map<String, Object> queryMap = getQueryMap(object);
        Map<String, String> queryMapStr = new HashMap<String, String>();
        for (String key : queryMap.keySet()) {
            queryMapStr.put(key, queryMap.get(key) == null ? null : queryMap.get(key).toString());
        }
        return queryMapStr;
    }
        /**
         * 将Query转化为Map，增加GroupBy属性
         * @author zhukun
         */
//	public static Map<String, Object> getQueryMap(Query query, String groupByField){
//		Map<String,Object> result = getQueryMap(query);
//		if(TBStringUtil.isNotEmpty(groupByField)){
//			result.put("groupByField", groupByField);
//		}
//		return result;
//	}

}
