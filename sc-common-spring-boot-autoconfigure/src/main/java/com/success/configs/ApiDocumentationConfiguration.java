package com.success.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@ConditionalOnClass(RestController.class)
public class ApiDocumentationConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public OpenAPI openAPIBean() {
    return new OpenAPI()
        .info(
            new Info()
                .title("My API Documentation")
                .description("My API Documentation")
                .version("1.0"));
  }
}
