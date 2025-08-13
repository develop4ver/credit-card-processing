package com.example.creditcardprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot application. This class bootstraps the
 * embedded web server and initialises the Spring context. Running the
 * {@code main} method will start the RESTful API on the configured port
 * (default 8080).
 */
@SpringBootApplication
public class CreditCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCardApplication.class, args);
    }
}