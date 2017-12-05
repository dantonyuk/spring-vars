package com.github.hyla.springvars;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockVarProvider implements VarConsumer {

    private Map<String, String> vars = new HashMap<>();

    public void setVar(String name, String value) {
        vars.put(name, value);
    }

    @Override
    public String findVar(Object caller, String name) {
        return vars.get(name);
    }
}
