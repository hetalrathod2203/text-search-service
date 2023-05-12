package com.mPokket.textsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TextSearchApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(TextSearchApplication.class, args);
    }
}
