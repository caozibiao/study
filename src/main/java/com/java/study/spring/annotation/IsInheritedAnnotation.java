package com.java.study.spring.annotation;

import java.lang.annotation.*;

/**
 * @Author zibiao cao
 * @date 2020/11/17 10:41
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface IsInheritedAnnotation {
}


