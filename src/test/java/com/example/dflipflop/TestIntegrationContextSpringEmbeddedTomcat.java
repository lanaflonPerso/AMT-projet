package com.example.dflipflop;

import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
Source :
https://www.baeldung.com/spring-boot-testresttemplate
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestIntegrationContextSpringEmbeddedTomcat {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void goodTest(){
        //String response = this.restTemplate.getForObject("http://localhost:" + port + "/",   String.class);

        ResponseEntity<String> response = restTemplate.
                getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
       // assertEquals(response, "Hello World");
    }

    @Test
    void badTest(){
        ResponseEntity<String> response = restTemplate.
                getForEntity("http://localhost:" + port + "/unknown", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
