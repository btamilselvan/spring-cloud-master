package com.success.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.success.models.Data;

import feign.Headers;

@FeignClient(name = "MyConfigApp", fallback = ConfigClientFallBack.class)
public interface ConfigClient {

  @GetMapping(path = "/data")
  Data getData();

  @GetMapping(path = "/dwp")
  @Headers({"X-head1: x-value", "X-heade2: y-value"})
  Data getDataWithParam(@SpringQueryMap Data data);
}
