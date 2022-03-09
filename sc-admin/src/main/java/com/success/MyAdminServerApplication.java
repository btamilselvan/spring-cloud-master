package com.success;

import java.time.Instant;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@RestController
public class MyAdminServerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MyAdminServerApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }

  @GetMapping("/health/check")
  public String healthCheck() {
    return "Hello.. You have reached the admin controller. current time is " + Instant.now();
  }
}
