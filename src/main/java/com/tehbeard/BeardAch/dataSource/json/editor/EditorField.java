package com.tehbeard.beardach.dataSource.json.editor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EditorField {
String alias();
EditorFieldType type() default EditorFieldType.text;
String[] options() default {};
}