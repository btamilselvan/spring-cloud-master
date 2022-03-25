package com.success.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.entities.Codes;
import com.success.services.SimpleService;

@RestController
@RequestMapping("/")
public class SimpleRestController {
  @Autowired private SimpleService service;

  @GetMapping
  public String findAll() {
    return service.findAll();
  }

  @PostMapping
  public String saveCode(@RequestBody Codes entity) {
    return service.save(entity);
  }

  @GetMapping("/a")
  public String findAllAnother() {
    return service.findAllAnother();
  }
}
