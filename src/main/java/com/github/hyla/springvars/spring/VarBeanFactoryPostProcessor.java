package com.github.hyla.springvars.spring;

import com.github.hyla.springvars.annotation.UsesVars;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class VarBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private VarScope varScope = new VarScope();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(UsesVars.SCOPE_NAME, varScope);
        beanFactory.registerSingleton("varScope", varScope);
    }
}
