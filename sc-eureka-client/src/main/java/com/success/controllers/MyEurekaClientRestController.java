package com.success.controllers;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class MyEurekaClientRestController {

  @Autowired private EurekaClient ec;

  @GetMapping
  public String home() {
    return "home";
  }

  @GetMapping("/health")
  public String health() {
    return Instant.now().toString();
  }

  @GetMapping("/all/instances")
  public Map<String, String> findAllInstances() {
    return ec.getApplications()
        .getRegisteredApplications()
        .stream()
        .flatMap(a -> a.getInstances().stream())
        .collect(Collectors.toMap(InstanceInfo::getInstanceId, InstanceInfo::toString));
  }
}
