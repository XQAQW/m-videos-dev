package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.czxy", "org.n3r.idworker"})
public class VideosManengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideosManengeApplication.class, args);
    }
}
