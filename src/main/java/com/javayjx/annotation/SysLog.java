package com.javayjx.annotation;



import java.lang.annotation.*;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/12 18:57
 * @description： 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SysLog {

    String descrption() default "" ;

    String operateType() default "" ;

    String modelType() default "";
}
