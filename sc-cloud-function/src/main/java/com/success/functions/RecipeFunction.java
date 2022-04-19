package com.success.functions;

import java.util.function.Function;

import org.joda.time.Instant;

import com.success.dtos.RecipeDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecipeFunction implements Function<RecipeDto, String> {

  @Override
  public String apply(RecipeDto t) {
    log.info(t.toString());
    return Instant.now().toString();
  }
}
