package com.success.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.success.models.Data;

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

  @GetMapping("/dwp")
  public Data dataWithParam(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "value", required = false) String value) {
    return new Data(name, value);
  }

  @GetMapping("/data")
  public Data someModel(@RequestHeader(name = "custom_header") String myHeader) {
    /*try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      log.error("", e);
    }*/
    log.info("header is {}", myHeader);
    return new Data(this.toString(), myHeader);
  }
}
