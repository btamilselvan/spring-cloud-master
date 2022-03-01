package com.success.controllers;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyGatewayController {

  @GetMapping("/health-check")
  public String healthCheck() {
    return "current time is " + Instant.now();
  }

  @GetMapping("/")
  public String m1() {
    log.info("inside gateway");
    return "current time is " + Instant.now();
  }
}
