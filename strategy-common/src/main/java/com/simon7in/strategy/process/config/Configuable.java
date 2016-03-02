/**
 * 
 */
package com.simon7in.strategy.process.config;

/**
 * <pre>
 * 标识某类是否支持特性配置
 * </pre>
 *
 * 目前支持配置的类有：
 * <li>{@link HandlerChainConfig}</li>
 * <li>{@link HandlerConfig}</li>
 *
 */
public interface Configuable {

	/**
	 * 获取一份配置，此时会创建一份Configurable实例。
	 * @return 配置实例
	 */
	Configuable config();
}
