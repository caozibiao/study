package com.java.study.spring.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * @Author zibiao cao
 * @date 2020/11/17 10:48
 * @Version 1.0
 */

public class MyInheritedClassTest {
    @Test
    public void testInherited(){
        {
            Annotation[] annotations = MyInheritedClass.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInherritedAnnotation.class)));
        }
        {
            Annotation[] annotations = MyInheritedClassUseInterface.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInherritedAnnotation.class)));
        }
        {
            Annotation[] annotations = IInheritedInterface.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(NoInherritedAnnotation.class)));
        }
        {
            Annotation[] annotations = IInheritedInterfaceChild.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInherritedAnnotation.class)));
        }
    }
}
