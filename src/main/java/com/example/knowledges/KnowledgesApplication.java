package com.example.knowledges;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.knowledges.mapper")
public class KnowledgesApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgesApplication.class, args);
    }

}
