package com.github.hyla.springvars;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class VarAspect {

    @Autowired
    private VarManager varManager;

    @Before("within(com.github.hyla.springvars.VarProvider+) && execution(* findVar(..))")
    public void beforeFindVar(JoinPoint pjp) {
        Object bean = pjp.getArgs()[0];
        String name = (String) pjp.getArgs()[1];
        varManager.bind(name, bean);
    }
}
