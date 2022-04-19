package com.success.functions;

import java.time.Instant;
import java.util.function.UnaryOperator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Reverse implements UnaryOperator<String> {

  @Override
  public String apply(String t) {
    log.info("input is {}", t);
    return new StringBuilder(t).reverse().toString().concat(" ").concat(Instant.now().toString());
  }
}
