package pzn.springaop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void helloService() {
        Assertions.assertEquals("Hello Alvenio", helloService.hello("Alvenio"));
        Assertions.assertEquals("Hello Alvenio Farhan", helloService.hello("Alvenio", "Farhan"));
        Assertions.assertEquals("Bye Alvenio", helloService.bye("Alvenio"));

        helloService.test();
    }
}