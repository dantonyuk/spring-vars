package com.github.hyla.springvars;

import com.github.hyla.springvars.annotation.UsesVars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@UsesVars
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    private VarProvider varProvider;

    private String value;

    @PostConstruct
    public void init() {
        value = varProvider.findVar(this,"importantValue");
    }

    @Override
    public String getValue() {
        return value;
    }
}
