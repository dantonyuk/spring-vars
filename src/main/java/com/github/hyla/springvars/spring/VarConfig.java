package com.github.hyla.springvars.spring;

import com.github.hyla.springvars.VarAspect;
import com.github.hyla.springvars.VarManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VarConfig {

    @Bean
    public VarBeanFactoryPostProcessor varBeanFactoryPostProcessor() {
        return new VarBeanFactoryPostProcessor();
    }

    @Bean
    public VarManager varManager() {
        return new VarManager();
    }

    @Bean
    public VarAspect varAspect() {
        return new VarAspect();
    }
}
