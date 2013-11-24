package com.tehbeard.beardach.dataSource.json.help;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface ComponentValueDescription {
String description();
String[] examples() default {};
}