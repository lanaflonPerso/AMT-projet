package com.example.dflipflop;

import com.example.dflipflop.Controllers.HomeController;
import org.hamcrest.MatcherAssert;
import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;
@SpringBootTest
class FlipFlopApplicationTests {

    @Autowired
    private HomeController controller;

    /**
     * Test that the context is creating the controller
     */
    @Test
    void contextLoads() {

        MatcherAssert.assertThat(controller, is(notNullValue()));
    }

}
