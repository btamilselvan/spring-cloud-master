package com.success;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyEurekaClientSpringApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MyEurekaClientSpringApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }
}
