package com.example.userregistrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserRegistrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationServiceApplication.class, args);
    }

}
