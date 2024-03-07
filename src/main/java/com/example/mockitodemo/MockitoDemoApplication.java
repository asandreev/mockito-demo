package com.example.mockitodemo;

import com.example.mockitodemo.services.Greeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MockitoDemoApplication implements CommandLineRunner {

    private final Greeter helloSayer;

    private final Logger logger = LoggerFactory.getLogger(MockitoDemoApplication.class);

    public MockitoDemoApplication(Greeter helloSayer) {
        this.helloSayer = helloSayer;
    }

    public static void main(String[] args) {
        SpringApplication.run(MockitoDemoApplication.class, args);
    }

    @Override public void run(String... args) throws Exception {
        logger.info(helloSayer.greeting("Andrey"));
    }
}
