package com.gitee.yoursmlie.bookstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.gitee.yoursmlie.bookstore.mapper"})
public class ZmbBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmbBookStoreApplication.class, args);
    }

}