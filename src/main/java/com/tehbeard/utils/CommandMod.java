package com.tehbeard.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface CommandMod {
String name();
String permission() default "";
String usage() default "/<command>";
String description() default "";
String[] alias() default {};
}
