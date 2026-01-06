package by.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntitiesApp {
    private static final Logger log = LoggerFactory.getLogger(EntitiesApp.class);

    public static void main(String[] args) {
        log.info("Starting EntitiesApp on port 24110...");
        SpringApplication.run(EntitiesApp.class, args);
        log.info("EntitiesApp started.");
    }
}
