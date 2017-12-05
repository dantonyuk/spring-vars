package com.github.hyla.springvars.annotation;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(UsesVars.SCOPE_NAME)
public @interface UsesVars {

    String SCOPE_NAME = "vars";
}
