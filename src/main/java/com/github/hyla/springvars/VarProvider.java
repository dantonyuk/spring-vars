package com.github.hyla.springvars;

public interface VarProvider {

    String findVar(Object caller, String name);
}
