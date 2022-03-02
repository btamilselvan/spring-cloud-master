package com.success.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.success.clients.ConfigClient;
import com.success.models.Data;

@RestController
public class MyFeignRestController {
  @Autowired private ConfigClient cc;

  @GetMapping("/health-check")
  public String health() {
    return "From open feign app" + Instant.now();
  }

  @GetMapping("/grd")
  public Data getRemoteData() {
    return cc.getData();
  }

  @GetMapping("/dwp")
  public Data getRemoteDataWithParam(
      @RequestParam(name = "name") String name,
      @RequestParam(name = "value", required = false) String value) {
    return cc.getDataWithParam(new Data(name, value));
  }
}
