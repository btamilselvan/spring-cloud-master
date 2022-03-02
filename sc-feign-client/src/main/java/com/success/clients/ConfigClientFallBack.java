package com.success.clients;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.success.models.Data;

@Component
public class ConfigClientFallBack implements ConfigClient {

  @Override
  public Data getData() {
    return new Data("FallBackData", Instant.now().toString());
  }

  @Override
  public Data getDataWithParam(Data data) {
    return getData();
  }
}
