package com.success.controllers;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CanonController {
  @GetMapping("/ping")
  public String ping() {
    return "current time is " + Instant.now().toString();
  }
}
