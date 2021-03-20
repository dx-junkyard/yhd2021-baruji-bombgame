package org.yhackday;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BombermanApplication {
    public static void main(String[] args){
        SpringApplication.run(BombermanApplication.class, args);
    }
}
