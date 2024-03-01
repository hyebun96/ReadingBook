package com.reading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReadingBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingBookApplication.class, args);
    }

}
