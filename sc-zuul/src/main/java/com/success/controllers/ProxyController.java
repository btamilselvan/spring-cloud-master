package com.success.controllers;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProxyController {

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public String sayHello() {
    return "Hello!, I am a proxy to my ndb micro services. My instance ID is " + this.toString();
  }
}
