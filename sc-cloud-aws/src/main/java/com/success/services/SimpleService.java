package com.success.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.success.entities.Codes;
import com.success.repositories.CodesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SimpleService {
  @Autowired private CodesRepository repo;

  @Value("${mysql.username}")
  private String username;

  @Transactional(readOnly = true)
  public String findAll() {
    log.info("username is {}", username);
    return repo.findAll()
        .stream()
        .map(c -> c.getId().getCoSubtype())
        .collect(Collectors.joining(","));
  }

  @Transactional
  public String save(Codes entity) {
    repo.save(entity);
    return entity.getId().getCoType();
  }

  public String findAllAnother() {
    return repo.findAll()
        .stream()
        .map(c -> c.getId().getCoSubtype())
        .collect(Collectors.joining(","));
  }
}
