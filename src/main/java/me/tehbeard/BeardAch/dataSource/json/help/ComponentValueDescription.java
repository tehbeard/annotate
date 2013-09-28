package me.tehbeard.BeardAch.dataSource.json.help;

public @interface ComponentValueDescription {
String description();
String[] examples() default {};
}
