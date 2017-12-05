package com.github.hyla.springvars;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
@WebMvcTest
public class VarTest {

    @Autowired
    private VarConsumer varProvider;

    @Autowired
    private VarManager varManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        varProvider.setVar("importantValue", "first");
        varManager.varChanged(Collections.singleton("importantValue"));
        expect("first");

        varProvider.setVar("importantValue", "second");
        expect("first");

        varManager.varChanged(Collections.singleton("importantValue"));
        expect("second");
    }

    private void expect(String value) throws Exception {
        mockMvc.perform(get("/value"))
                .andExpect(status().isOk())
                .andExpect(content().string(value));
    }
}
