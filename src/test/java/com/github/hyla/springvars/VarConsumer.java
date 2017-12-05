package com.github.hyla.springvars;

public interface VarConsumer extends VarProvider {

    void setVar(String name, String value);
}
