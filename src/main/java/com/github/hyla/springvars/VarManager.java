package com.github.hyla.springvars;

import com.github.hyla.springvars.spring.VarScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class VarManager {

    @Autowired
    private VarScope varScope;

    private MultiValueMap<String, Object> beansByVars = new LinkedMultiValueMap<>();

    public void bind(String varName, Object bean) {
        beansByVars.add(varName, bean);
    }

    public void varChanged(Collection<String> vars) {
            vars.stream()
                    .flatMap(c -> {
                        // awful but concise
                        List<Object> beans = beansByVars.remove(c);
                        return beans == null ? Stream.empty() : beans.stream();
                    })
                    .forEach(varScope::removeBean);
    }
}
