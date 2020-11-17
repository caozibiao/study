package com.java.study.spring.annotation;

/**
 * @Author zibiao cao
 * @date 2020/11/17 10:47
 * @Version 1.0
 */
@NoInherritedAnnotation
@IsInheritedAnnotation
public interface IInheritedInterface {
}

interface IInheritedInterfaceChild extends IInheritedInterface {
}

class MyInheritedClassUseInterface implements IInheritedInterface {
}

