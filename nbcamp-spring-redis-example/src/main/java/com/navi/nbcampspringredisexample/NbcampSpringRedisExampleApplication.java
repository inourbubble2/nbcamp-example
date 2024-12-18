package com.navi.nbcampspringredisexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NbcampSpringRedisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbcampSpringRedisExampleApplication.class, args);
    }

}
