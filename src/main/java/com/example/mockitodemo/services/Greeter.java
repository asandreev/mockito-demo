package com.example.mockitodemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Greeter {

    private final Logger logger = LoggerFactory.getLogger(Greeter.class);

    public String greeting(String name) {
        logger.info("Real method invocation!!!!");
        return "Hello, " + name;
    }

}
