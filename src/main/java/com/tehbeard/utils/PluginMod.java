package com.tehbeard.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface PluginMod {
public enum LoadType{
STARTUP,
POSTWORLD
}
String name();
String version();
String description() default "";
String website() default "";
LoadType load() default LoadType.POSTWORLD;
String[] authors() default {};
String[] depend() default {};
String[] softdepend() default {};
String[] loadbefore() default {};
boolean database() default false;
}
