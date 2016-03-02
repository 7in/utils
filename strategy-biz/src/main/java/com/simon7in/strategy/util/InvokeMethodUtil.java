package com.simon7in.strategy.util;

import com.simon7in.strategy.bo.InputArgBO;
import com.google.common.primitives.Primitives;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiayu.shenjy on 2016/1/18.
 */
@Repository("invokeMethodUtil")
public class InvokeMethodUtil implements BeanFactoryAware {
    protected static BeanFactory beanFactory;

    /**
     * 通过反射方式执行接口方法
     *通过bean类名找bean执行
     * @param beanName
     * @param methodName
     * @param inputArgBOs
     * @throws Exception
     */
    public static void invokeByClassType(String beanName, String methodName, List<InputArgBO> inputArgBOs) throws Exception {
        Class clazz = Class.forName(beanName);
        Object bean = beanFactory.getBean(clazz);
        invoke(bean, methodName, inputArgBOs);
    }

    /**
     * 通过bean id找bean并反射执行
     * @param beanName
     * @param methodName
     * @param inputArgBOs
     * @throws Exception
     */
    public static void invokeByBeanName(String beanName, String methodName,List<InputArgBO> inputArgBOs) throws Exception {
        Object bean = beanFactory.getBean(beanName);
        invoke(bean, methodName, inputArgBOs);
    }

    /**
     * 通过反射的方式执行
     * @param bean
     * @param methodName
     * @param inputArgBOs
     * @throws Exception
     */
    public static void invoke(Object bean, String methodName, List<InputArgBO> inputArgBOs) throws Exception {
        Class<?>[] clazzs = new Class<?>[inputArgBOs.size()];
        Object[] parameters = new Object[inputArgBOs.size()];
        Method method = null;
        for (int i = 0; i < clazzs.length; i++) {
            clazzs[i] = inputArgBOs.get(i).getClazz();
            parameters[i]=inputArgBOs.get(i).getInputArg();
        }
        Method[] methods = bean.getClass().getMethods();
        List<Method> temps = filter(methods, methodName);
        if (temps.size() == 1) {
            method = temps.get(0);
        } else {
            for (Method tempMethod : temps) {
                boolean flag = true;
                Class<?>[] types = tempMethod.getParameterTypes();
                if (types.length != clazzs.length) {
                    continue;
                }
                for (int j = 0; j < types.length; j++) {
                    if (!Primitives.wrap(types[j]).equals(Primitives.wrap(clazzs[j]))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    method = tempMethod;
                    break;
                }
            }
        }
        if (method == null) {
            throw new Exception("can not find invoke method!");
        }
        method.invoke(bean, parameters);
    }

    /**
     * 确定具体的方法
     *
     * @param methods
     * @param methodName
     * @return
     */
    private static List<Method> filter(Method[] methods, String methodName) {
        List<Method> list = new ArrayList<Method>();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(methodName)) {
                list.add(methods[i]);
            }
        }
        return list;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
