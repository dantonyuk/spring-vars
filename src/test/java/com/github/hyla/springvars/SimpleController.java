package com.github.hyla.springvars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/value")
    public String getValue() {
        return simpleService.getValue();
    }
}
