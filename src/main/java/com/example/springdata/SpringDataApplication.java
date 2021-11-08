package com.example.springdata;

import com.example.springdata.service.ProductService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringDataApplication {

    //шифруем пароль
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
//        Flyway.configure()
//                .baselineOnMigrate(true)
//                .load();
        SpringApplication.run(SpringDataApplication.class, args);

    }

}
