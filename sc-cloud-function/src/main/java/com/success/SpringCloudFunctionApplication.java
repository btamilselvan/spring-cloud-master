package com.success;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudFunctionApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudFunctionApplication.class, args);
  }

  /*@Bean
  public Function<String, String> toUppercase() {
    return String::toUpperCase;
  }*/
}