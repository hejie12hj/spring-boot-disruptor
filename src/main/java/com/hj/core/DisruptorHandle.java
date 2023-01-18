package com.hj.core;


import com.hj.annotation.DisruptorListener;
import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消费者
 *
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:41 AM
 */
public class DisruptorHandle implements EventHandler<DisruptorEvent>, BeanPostProcessor {

    /**
     * 注解缓存map
     */
    private Map<String, MethodInfo> methodMap = new HashMap<>();

    Logger logger = org.slf4j.LoggerFactory.getLogger(DisruptorHandle.class);

    @Override
    public void onEvent(DisruptorEvent disruptorEvent, long l, boolean b) throws Exception {
        logger.info("消费消息：type={},jsonData={}", disruptorEvent.getType(), disruptorEvent.getJsonData());
        MethodInfo methodInfo = methodMap.get(disruptorEvent.getType());
        if (methodInfo != null) {
            methodInfo.getMethod().invoke(methodInfo.getBean(), disruptorEvent.getJsonData());
        }
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithMethods(bean.getClass(), method -> {
            DisruptorListener annotation = method.getAnnotation(DisruptorListener.class);
            if (annotation != null) {
                MethodInfo methodInfo = new MethodInfo();
                methodInfo.setMethod(method);
                methodInfo.setBean(bean);
                methodMap.put(annotation.type(), methodInfo);
            }
        });
        return bean;
    }
}
