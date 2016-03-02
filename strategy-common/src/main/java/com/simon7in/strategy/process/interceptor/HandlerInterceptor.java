/**
 * 
 */
package com.simon7in.strategy.process.interceptor;


import com.simon7in.strategy.process.HandleResult;
import com.simon7in.strategy.process.handler.Handler;

/**
 * 处理节点拦截器
 *
 */
public interface HandlerInterceptor<T> {

	void beforeHandle(T param, Handler<T> handler, HandleResult result);

	void afterHandle(T param, Handler<T> handler, HandleResult result);

	void errorHandle(T param, Handler<T> handler, HandleResult result);

	void finalHandle(T param, Handler<T> handler, HandleResult result);
}
