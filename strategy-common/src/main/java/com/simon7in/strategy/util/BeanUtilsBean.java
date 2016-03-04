package com.simon7in.strategy.util;


import org.apache.commons.beanutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 7in
 * Date: 14-6-29
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtilsBean {

    /**
     * Contains <code>BeanUtilsBean</code> instances indexed by context classloader.
     */
    private static final ContextClassLoaderLocal
            beansByClassLoader = new ContextClassLoaderLocal() {
        // Creates the default instance used when the context classloader is unavailable
        protected Object initialValue() {
            return new BeanUtilsBean();
        }
    };

    /**
     * Gets the instance which provides the functionality for {@link BeanUtils}.
     * This is a pseudo-singleton - an single instance is provided per (thread) context classloader.
     * This mechanism provides isolation for web apps deployed in the same container.
     */
    public synchronized static BeanUtilsBean getInstance() {
        return (BeanUtilsBean) beansByClassLoader.get();
    }

    /**
     * Sets the instance which provides the functionality for {@link BeanUtils}.
     * This is a pseudo-singleton - an single instance is provided per (thread) context classloader.
     * This mechanism provides isolation for web apps deployed in the same container.
     */
    public synchronized static void setInstance(BeanUtilsBean newInstance) {
        beansByClassLoader.set(newInstance);
    }

    // --------------------------------------------------------- Attributes

    /**
     * Logging for this instance
     */
    private Logger log = LoggerFactory.getLogger(BeanUtils.class);

    /** Used to perform conversions between object types when setting properties */
    private ConvertUtilsBean convertUtilsBean;

    /** Used to access properties*/
    private PropertyUtilsBean propertyUtilsBean;

    // --------------------------------------------------------- Constuctors

    /**
     * <p>Constructs an instance using new property
     * and conversion instances.</p>
     */
    public BeanUtilsBean() {
        this(new ConvertUtilsBean(), new PropertyUtilsBean());
    }

    /**
     * <p>Constructs an instance using given property and conversion instances.</p>
     *
     * @param convertUtilsBean use this <code>ConvertUtilsBean</code>
     * to perform conversions from one object to another
     * @param propertyUtilsBean use this <code>PropertyUtilsBean</code>
     * to access properties
     */
    public BeanUtilsBean(
            ConvertUtilsBean convertUtilsBean,
            PropertyUtilsBean propertyUtilsBean) {

        this.convertUtilsBean = convertUtilsBean;
        this.propertyUtilsBean = propertyUtilsBean;
    }

    /**
     * <p>Return the entire set of properties for which the specified bean
     * provides a read method. This map contains the to <code>String</code>
     * converted property values for all properties for which a read method
     * is provided (i.e. where the getReadMethod() returns non-null).</p>
     *
     * <p>This map can be fed back to a call to
     * <code>BeanUtils.populate()</code> to reconsitute the same set of
     * properties, modulo differences for read-only and write-only
     * properties, but only if there are no indexed properties.</p>
     *
     * @param bean Bean whose properties are to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    public Map describe(Object bean)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        if (bean == null) {
            //            return (Collections.EMPTY_MAP);
            return (new HashMap());
        }

        log.debug("Describing bean: " + bean.getClass().getName());

        Map description = new HashMap();
        if (bean instanceof DynaBean) {
            DynaProperty descriptors[] =
                    ((DynaBean) bean).getDynaClass().getDynaProperties();
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                description.put(name, getProperty(bean, name));
            }
        } else {
            PropertyDescriptor descriptors[] =
                    getPropertyUtils().getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (descriptors[i].getReadMethod() != null)
                    description.put(name, getProperty(bean, name));
            }
        }
        return (description);

    }

    /**
     * Return the value of the specified property of the specified bean,
     * no matter which property reference format is used, as a String.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Possibly indexed and/or nested name of the property
     *  to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    public Object getProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return (getNestedProperty(bean, name));

    }

    /**
     * Return the value of the (possibly nested) property of the specified
     * name, for the specified bean, as a String.
     *
     * @param bean Bean whose property is to be extracted
     * @param name Possibly nested name of the property to be extracted
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception IllegalArgumentException if a nested reference to a
     *  property returns null
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    public Object getNestedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        Object value = getPropertyUtils().getNestedProperty(bean, name);
        return value;

    }

    /**
     * Gets the <code>ConvertUtilsBean</code> instance used to perform the conversions.
     */
    public ConvertUtilsBean getConvertUtils() {
        return convertUtilsBean;
    }

    /**
     * Gets the <code>PropertyUtilsBean</code> instance used to access properties.
     */
    public PropertyUtilsBean getPropertyUtils() {
        return propertyUtilsBean;
    }

}
