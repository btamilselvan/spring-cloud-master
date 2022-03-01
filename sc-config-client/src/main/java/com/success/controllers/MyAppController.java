package com.success.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyAppController {

  @Autowired private RestTemplate rt;
  @Autowired private DiscoveryClient dc;

  @GetMapping
  public String m1() {
    return "from M1. current time is " + Instant.now();
  }

  @GetMapping("/rc")
  public String m2() {
    String response = rt.getForObject("http://MyConsulApp/", String.class);
    log.info("response received from MyConsulApp {}", response);
    return "from M2. current time is " + Instant.now();
  }

  @GetMapping("/dc")
  public String m3() {
    dc.getInstances("MyConsulApp").forEach(si -> log.info("instance id {}", si.toString()));
    return "from M3. current time is " + Instant.now();
  }

  @GetMapping("/health-check")
  public String health() {
    return "From config app" + Instant.now();
  }
}
