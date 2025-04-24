package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class InfoController {

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        logger.info("Was invoked method to get port");
        return port;
    }

    @GetMapping("/fast-sum")
    public int getFastSum() {
        logger.info("Was invoked method to calculate fast sum");
        long startTime = System.currentTimeMillis();

        int sum = IntStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0, Integer::sum);

        long endTime = System.currentTimeMillis();
        logger.debug("Sum calculated in " + (endTime - startTime) + " ms");

        return sum;
    }
}