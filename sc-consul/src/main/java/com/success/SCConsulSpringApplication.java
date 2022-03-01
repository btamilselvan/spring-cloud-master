package com.success;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@Slf4j
@RefreshScope
public class SCConsulSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(SCConsulSpringApplication.class, args);
  }
  
  @Value("${xyz}")
  private String myProp;

  @GetMapping("/")
  public String m1() {
    log.info("myProp {}", this.myProp);
    return "Hello" + Instant.now();
  }

  @GetMapping("/health-check")
  public String health() {
    return "From consul app" + Instant.now();
  }
}
