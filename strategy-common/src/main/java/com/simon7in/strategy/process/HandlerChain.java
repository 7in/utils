package com.simon7in.strategy.process;

import com.simon7in.strategy.process.appender.Appender;
import com.simon7in.strategy.process.config.HandlerChainConfig;
import com.simon7in.strategy.process.config.HandlerConfig;
import com.simon7in.strategy.process.handler.AbstractHandler;
import com.simon7in.strategy.process.handler.Handler;
import com.simon7in.strategy.process.interceptor.HandlerInterceptor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class HandlerChain<T> implements Appender<T> {
    private final List<Handler<T>>            chain        = new ArrayList<Handler<T>>();

    private final List<HandlerInterceptor<T>> interceptors = new ArrayList<HandlerInterceptor<T>>();

    public HandleResult handle(HandlerChainConfig config, T param) throws HandleException {
        HandlerChainConfig cfg = null;
        if (config == null) {
            cfg = HandlerChainConfig.getDefault();
        } else {
            cfg = config;
        }

        HandleResult result = new HandleResult();

        Handler<T> currentHandler = null;
        boolean continuous = true;

        // BIZ HANDLE
        while (continuous && !result.isDone()
                && (currentHandler = nextHandler(currentHandler)) != null) {
            continuous = dealHandler(param, cfg, result, currentHandler);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private boolean dealHandler(T param, HandlerChainConfig cfg, HandleResult result,
                                Handler<T> currentHandler) {
        if (currentHandler.canHandle(param)) {
            HandlerConfig<Object> handlerConfig = (HandlerConfig<Object>) currentHandler
                    .getConfig();
            if (handlerConfig == null) {
                handlerConfig = HandlerConfig.getDefault();
            }

            try {
                intercept(param, currentHandler, result, handlerConfig, Location.BEFORE);

                currentHandler.handle(param, result);

                intercept(param, currentHandler, result, handlerConfig, Location.AFTER);

                if (cfg.isStopOnFailure() && !result.success) {
                    return false;
                }
            } catch (Exception e) {
                intercept(param, currentHandler, result, handlerConfig, Location.EXCEPTION);

                if (handlerConfig.isIgnoreException()) {
                    return true;
                }

                HandleException he = null;
                if (e instanceof HandleException) {
                    he = (HandleException) e;
                } else {
                    he = new HandleException("GENERIC_FAILURE", "handle chain error.", e);
                }

                result.exceptions.add(he);

                if (cfg.isThrowException()) {
                    throw he;
                }

                if (cfg.isStopOnException()) {
                    result.success = false;
                    return false;
                } else {
                    // CONTINUE
                    return true;
                }
            } finally {
                intercept(param, currentHandler, result, handlerConfig, Location.FINAL);
            }
        }

        return true;
    }

    protected Handler<T> nextHandler(Handler<T> currentHandler) {
        if (chain.size() == 0) {
            return null;
        }

        if (currentHandler == null) {
            return chain.get(0);
        }

        if (currentHandler.successor() != null) {
            int succIndex = chain.indexOf(currentHandler.successor());

            HandlerAssert.assertTrue(succIndex >= 0,
                    "successor [{0}] of [{1}] not found in the chain!", currentHandler.successor()
                            .getClass().getSimpleName(), currentHandler.getClass().getSimpleName());

            return currentHandler.successor();
        }

        int currIndex = chain.indexOf(currentHandler);

        HandlerAssert.assertTrue(currIndex >= 0, "illegal handler, not found in the chain!");

        // �Ѿ����
        if (currIndex == chain.size() - 1) {
            return null;
        } else {
            return chain.get(currIndex + 1);
        }
    }

    public boolean canHandle(T param) {
        return true;
    }

    public Appender<T> appendHandler(Handler<T> handler) {
        chain.add(handler);
        return this;
    }

    public Appender<T> appendHandler(Handler<T> handler, Handler<T> successor) {
        return appendHandler(handler.successor(successor));
    }

    public Appender<T> appendInterceptor(HandlerInterceptor<T> interceptor) {
        interceptors.add(interceptor);
        return this;
    }

    private void intercept(T param, Handler<T> handler, HandleResult result,
                           HandlerConfig<Object> config, Location loc) {
        if (config.isRefuseInterceptor()) {
            return;
        }

        for (HandlerInterceptor<T> hi : interceptors) {
            switch (loc) {
                case BEFORE:
                    hi.beforeHandle(param, handler, result);
                    break;
                case AFTER:
                    hi.afterHandle(param, handler, result);
                    break;
                case EXCEPTION:
                    hi.errorHandle(param, handler, result);
                    break;
                case FINAL:
                    hi.finalHandle(param, handler, result);
                    break;
                default:
                    break;
            }
        }
    }

    public static enum Location {
        BEFORE,
        AFTER,
        FINAL,
        EXCEPTION
    }

    private static class HandlerAssert {

        static void assertTrue(boolean statement, String errorMessage) {
            if (!statement) {
                throw new HandleException("HANDLER_CHAIN_ILLEGALE", errorMessage);
            }
        }

        static void assertTrue(boolean statement, String messageTemplete, Object... params) {
            if (!statement) {
                throw new HandleException("HANDLER_CHAIN_ILLEGALE", MessageFormat.format(
                        messageTemplete, params));
            }
        }
    }

    final Handler<T> NULL_HANDLER = new AbstractHandler<T>() {

                                      public boolean canHandle(T param) {
                                          return false;
                                      }

                                      public HandlerConfig<T> getConfig() {
                                          return null;
                                      }

                                      public void bizHandle(T param, HandleResult result) {
                                      }
                                  };
}
