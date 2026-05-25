package org.miwa.miwa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.miwa.miwa.mapper")
public class MiwaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiwaApplication.class, args);
    }

}
