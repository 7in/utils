/**
 * 
 */
package com.simon7in.strategy.process.appender;


import com.simon7in.strategy.process.handler.Handler;
import com.simon7in.strategy.process.interceptor.HandlerInterceptor;

/**
 * <pre>
 * 标识某个类是否支持添加处理节点
 * </pre>
 *
 * 目前支持该特性的类有：
 * <li>{@link HandlerChain}</li>
 * <li>{@link CompositeHandler}</li>
 * <li>{@link MutexHandler}</li>
 *
 */
public interface Appender<T> {

	/**
	 * 添加一个处理节点
	 *
	 * @param handler 处理节点
	 * @return
	 */
	Appender<T> appendHandler(Handler<T> handler);

	/**
	 * 添加一个处理节点，并指定该节点的下一个节点
	 *
	 * @param handler 处理节点
	 * @param successor 该节点的下一个节点
	 * @return
	 */
	Appender<T> appendHandler(Handler<T> handler, Handler<T> successor);

	/**
	 * 添加一个处理拦截器，该拦截器会应用到{@link HandlerChain}中每一个处理节点
	 * @param interceptor 拦截器
	 * @return
	 */
	Appender<T> appendInterceptor(HandlerInterceptor<T> interceptor);
}
