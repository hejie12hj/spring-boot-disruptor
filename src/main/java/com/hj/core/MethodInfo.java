package com.hj.core;

import java.lang.reflect.Method;

/**
 * @author: hj
 * @date: 2023/1/18
 * @time: 10:06 AM
 */
public class MethodInfo {

    Method method;

    Object bean;


    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
