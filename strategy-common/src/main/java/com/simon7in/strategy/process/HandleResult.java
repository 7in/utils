/**
 * 
 */
package com.simon7in.strategy.process;

import com.simon7in.strategy.process.context.AbstractContext;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class HandleResult extends AbstractContext {

    /** 是否退出责任链后续handler的执行 */
    boolean         done         = false;

    /** 是否成功 */
    boolean         success      = true;

    /** 错误编码 */
    String          errorCode    = null;

    /** 错误信息 */
    String          errorMessage = null;

    /** 如果设置 {@link HandlerChainConfig#stopOnException} = false，则异常列表可以大于1. */
    List<Throwable> exceptions   = new ArrayList<Throwable>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Throwable> exceptions) {
        this.exceptions = exceptions;
    }

    public void addExceptions(Throwable exception) {
        if (exceptions == null) {
            exceptions = new ArrayList<Throwable>();
        }
        exceptions.add(exception);
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
