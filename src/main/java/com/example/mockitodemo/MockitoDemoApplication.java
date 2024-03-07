package com.example.mockitodemo;

import com.example.mockitodemo.services.Brick;
import com.example.mockitodemo.services.BrickFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MockitoDemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(MockitoDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MockitoDemoApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        Brick brick = BrickFactory.getFactory().getBrick();
        logger.info(brick.getColor());
    }
}
