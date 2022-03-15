package com.success;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
public class MySCGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(MySCGatewayApplication.class, args);
  }
}
