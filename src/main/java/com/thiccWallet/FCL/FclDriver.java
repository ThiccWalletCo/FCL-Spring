package com.thiccWallet.FCL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // implies: @Configuration, @AutoConfiguration (does the @Enable___ for us), @ComponentScan
public class FclDriver {

    public static void main(String[] args) {
        SpringApplication.run(FclDriver.class, args);
    }

}