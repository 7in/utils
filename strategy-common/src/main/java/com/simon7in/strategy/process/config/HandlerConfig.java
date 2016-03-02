/**
 * 
 */
package com.simon7in.strategy.process.config;


import com.simon7in.strategy.process.context.AbstractContext;

/**
 * <pre>
 * 处理节点的配置信息，如可以设置是否忽略异常等
 * </pre>
 *
 * 除已定义的配置项外，还可以自定义配置，通过
 * 设置。
 *
 */
public class HandlerConfig<T> extends AbstractContext implements Configuable {
    /**
     * 是否忽略该节点异常，默认不忽略
     */
    private boolean                      ignoreException   = false;

    /**
     * 是否拒绝拦截器监视，默认不拒绝
     */
    private boolean                      refuseInterceptor = false;

    /**
     * 全局默认节点配置
     */
    static private HandlerConfig<Object> DEFAULT_CONFIG    = new HandlerConfig<Object>();

    private HandlerConfig() {
    }

    public HandlerConfig<T> config() {
        return new HandlerConfig<T>();
    }

    public static HandlerConfig<Object> getDefault() {
        return DEFAULT_CONFIG;
    }

    public boolean isIgnoreException() {
        return ignoreException;
    }

    public HandlerConfig<T> setIgnoreException(boolean ignoreException) {
        this.ignoreException = ignoreException;
        return this;
    }

    public boolean isRefuseInterceptor() {
        return refuseInterceptor;
    }

    public void setRefuseInterceptor(boolean refuseInterceptor) {
        this.refuseInterceptor = refuseInterceptor;
    }
}
