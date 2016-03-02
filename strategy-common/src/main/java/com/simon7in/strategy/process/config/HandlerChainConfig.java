/**
 * 
 */
package com.simon7in.strategy.process.config;


import com.simon7in.strategy.process.context.AbstractContext;

/**
 * <pre>
 * 处理链路的配置信息
 * </pre>
 *
 */
public class HandlerChainConfig extends AbstractContext implements Configuable {

    /**
     * 遇到异常是否终止，如果false，则chain会执行完，并将异常收集到ExceptionList中
     */
    boolean                                 stopOnException = true;

    /**
     * =false时，是否退出执行责任链
     */
    boolean                                 stopOnFailure   = true;

    /**
     * 是否直接将异常抛出
     */
    private boolean                         throwException  = true;

    /**
     * 默认配置
     */
    private final static HandlerChainConfig DEFAULT_CONFIG  = new HandlerChainConfig();

    private HandlerChainConfig() {
    }

    public HandlerChainConfig config() {
        return new HandlerChainConfig();
    }

    /**
     * 遇到异常是否终止
     *
     * @return
     */
    public boolean isStopOnException() {
        return stopOnException;
    }

    /**
     * 设置遇到异常是否终止
     *
     * @param stopOnException
     */
    public void setStopOnException(boolean stopOnException) {
        this.stopOnException = stopOnException;
    }

    /**
     * 遇到失败，是否退出执行责任链
     *
     * @return
     */
    public boolean isStopOnFailure() {
        return stopOnFailure;
    }

    /**
     * 设置遇到失败，是否退出执行责任链
     *
     * @param stopOnFailure
     */
    public void setStopOnFailure(boolean stopOnFailure) {
        this.stopOnFailure = stopOnFailure;
    }

    /**
     * 获取默认配置
     *
     * @return
     */
    public static HandlerChainConfig getDefault() {
        return DEFAULT_CONFIG;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public void setThrowException(boolean throwException) {
        this.throwException = throwException;
    }
}
