package com.dxl.fc;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dxl.fc.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
@SpringBootApplication
public class FcApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcApplication.class, args);
    }

}
