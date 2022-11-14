package com.success.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.success.BootRestLambdaApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamLambdaHandler implements RequestStreamHandler {
  //  private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
  private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

  /*static {
    try {
      handler =
          SpringBootLambdaContainerHandler.getAwsProxyHandler(BootRestLambdaApplication.class);
    } catch (Exception e) {
      log.error("unable to initialize SpringBootLambdaContainerHandler", e);
      throw new RuntimeException(e);
    }
  }*/

  public StreamLambdaHandler() throws ContainerInitializationException {
    String profile = System.getenv("profile");
    if (profile == null) {
      profile = "default";
    }
    log.info("activated profile {}", profile);
    handler =
        new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
            .springBootApplication(BootRestLambdaApplication.class)
            .defaultProxy()
            .asyncInit()
            .profiles(profile)
            .buildAndInitialize();
  }

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context)
      throws IOException {
    handler.proxyStream(input, output, context);
  }
}
