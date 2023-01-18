package com.hj.annotation;

import java.lang.annotation.*;

/**
 * 消息订阅注解
 *
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:59 AM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisruptorListener {
    String type();
}
