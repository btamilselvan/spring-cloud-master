package com.success;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyEurekaServerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MyEurekaServerApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }
}
