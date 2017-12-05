package com.github.hyla.springvars;

import com.github.hyla.springvars.annotation.EnableVarScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@ComponentScan(value = "com.github.hyla.springvars", scopedProxy = ScopedProxyMode.INTERFACES)
@EnableAspectJAutoProxy
@EnableVarScope
public class TestConfiguration {
}
