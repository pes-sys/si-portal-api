package com.esstm.siportalapi;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(
        {

                "com.esstm.siportalapi.domain.member.repository",
                "com.esstm.siportalapi.domain.post.repository"
        }

)
public class SiPotalApiApplication {

    private static final Logger log = LoggerFactory.getLogger(SiPotalApiApplication.class);

    public static void main(String[] args) {
        log.info("<<< SI PORTER API {} >>>", "START");
        SpringApplication.run(SiPotalApiApplication.class, args);
    }

}
