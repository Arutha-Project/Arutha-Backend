package com.arutha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Arutha Application.
 */
@SpringBootApplication
public class AruthaApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/api");
        SpringApplication.run(AruthaApplication.class, args);
    }

}
