package com.success;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class MyProxyApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MyProxyApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }
}
