package com.cod.desktop.ironman.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 给VO属性加上该注解，令其不会被持久化火序列化（如properties文件读写）
 * @author EX_WLJR_YULONG
 * @2019年11月17日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}
