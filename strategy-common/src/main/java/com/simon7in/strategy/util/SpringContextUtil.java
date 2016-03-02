package com.simon7in.strategy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiayu.shenjy on 2016/2/26.
 */
public class SpringContextUtil implements  ApplicationContextAware{
    private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
    public static ApplicationContext getAppContext(){
        return applicationContext;
    }

    public static void injectFieldBean(Class<? extends Annotation> annotationType, Object clazzObj) throws Exception {
            Set<Field> fields = getFieldsAnnotatedWith(clazzObj.getClass(), annotationType);

            for (Field field : fields) {
                if (!hasBeanInjected(field, clazzObj)) {
                    setFieldValue(clazzObj, field, getBean(field.getName(), applicationContext));
                }
            }
    }
    /**
     * Returns the given class's declared fields that are marked with the given annotation
     *
     * @param clazz      The class, not null
     * @param annotation The annotation, not null
     * @return A List containing fields annotated with the given annotation, empty list if none found
     */
    public static <T extends Annotation> Set<Field> getFieldsAnnotatedWith(Class<? extends Object> clazz, Class<T> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }
        Set<Field> annotatedFields = new HashSet<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null) {
                annotatedFields.add(field);
            }
        }
        annotatedFields.addAll(getFieldsAnnotatedWith(clazz.getSuperclass(), annotation));
        return annotatedFields;
    }

    private static boolean hasBeanInjected(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Sets the given value to the given field on the given object
     *
     * @param object The object containing the field, not null
     * @param field  The field, not null
     * @param value  The value for the given field in the given object
     * @throws Exception if the field could not be accessed
     */
    public static void setFieldValue(Object object, Field field, Object value) throws Exception {
        try {
            field.setAccessible(true);
            field.set(object, value);

        } catch (IllegalArgumentException e) {
            throw new Exception("Unable to assign the value to field: " + field.getName() + ". Ensure that this field is of the correct type. Value: " + value, e);

        } catch (IllegalAccessException e) {
            // Cannot occur, since field.accessible has been set to true
            throw new Exception("Error while trying to access field " + field, e);
        }
    }

    private static Object getBean(String name, ApplicationContext applicationContext) {

        Object bean = applicationContext.getBean(name);

//        if (Proxy.isProxyClass(bean.getClass())) {
//            ServiceUtil.waitServiceReady(bean);
//            return bean;
//        }

        // if the bean to be injected is a proxy object,then wait and return;
        // else recursively travel all fields
        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (hasBeanInjected(field, bean)) {
                if (applicationContext.containsBean(field.getName())) {
                    Object param = getBean(field.getName(), applicationContext);
                    if (param != null && Proxy.isProxyClass(param.getClass())) {
//                        ServiceUtil.waitServiceReady(param);
                    }
                }
            }
        }
        return bean;

    }
}
