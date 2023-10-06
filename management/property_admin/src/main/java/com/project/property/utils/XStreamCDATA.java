package com.project.property.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Mr.Wang
 * @Date 2020/9/14
 * @Description 下一位读我代码的人, 有任何疑问请联系我, QQ：943701114
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XStreamCDATA {
    
}