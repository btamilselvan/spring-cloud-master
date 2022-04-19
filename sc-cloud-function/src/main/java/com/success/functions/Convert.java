package com.success.functions;

import java.util.function.Function;

public class Convert implements Function<Integer, String> {

  @Override
  public String apply(Integer t) {
    if (t != null) {
      return t.toString();
    }
    return null;
  }
}
