/**
 * 
 */
package com.simon7in.strategy.process.handler;


import com.simon7in.strategy.process.HandleResult;
import com.simon7in.strategy.process.config.HandlerConfig;

/**
 * 处理节点
 *
 */
public interface Handler<T> {

	/**
	 * 该节点是否接受传入的参数，如果不接受，将不会执行handle主体内容。
	 * @param param 传入的参数
	 * @return
	 */
	boolean canHandle(T param);

	/**
	 * 处理主体内容
	 * @param param 传入的参数
	 * @param result 传入时是上一个节点处理结果，当前节点完成后，即是当前节点执行结果
	 */
	void handle(T param, HandleResult result);

	/**
	 * 下一个节点，可能为空。如果为空，{@link HandlerChain}会自动计算下一个节点
	 * @return
	 */
	Handler<T> successor();

	/**
	 * 设置下一个节点
	 * @param successor
	 * @return
	 */
	Handler<T> successor(Handler<T> successor);

	/**
	 * 节点配置信息
	 * @return
	 */
	HandlerConfig<T> getConfig();
}
