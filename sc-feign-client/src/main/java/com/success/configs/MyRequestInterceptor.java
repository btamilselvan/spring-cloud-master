package com.success.configs;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
    log.info("inside MyRequestInterceptor");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      // get the token/or whatever is needed from here and set it in the header..
    }
    // set a custom header
    template.header("custom_header", Instant.now().toString());
  }
}
