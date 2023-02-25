package com.example.ottokeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OttokengApplication {

    public static void main(String[] args) {
        SpringApplication.run(OttokengApplication.class, args);
    }

}
