package com.tehbeard.twobyfour.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Labels a plank
 * @author James
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PlankLabel {
String id();
String name();
String version() default "1.0";
}
