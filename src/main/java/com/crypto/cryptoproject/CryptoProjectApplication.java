package com.crypto.cryptoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//TODO that only for test
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ServletComponentScan
public class CryptoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoProjectApplication.class, args);
    }

}
