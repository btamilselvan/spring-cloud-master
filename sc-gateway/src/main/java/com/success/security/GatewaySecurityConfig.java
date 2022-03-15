package com.success.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
  @Bean
  @Profile("resourceserver")
  public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {

    return http.authorizeExchange()
        .pathMatchers("/health-check")
        .permitAll()
        .and()
        .authorizeExchange()
        .anyExchange()
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt()
        .and()
        .and()
        .build();
  }

  @Bean
  @Profile("oauthclient")
  public SecurityWebFilterChain securitygWebFilterChainForOauthClient(ServerHttpSecurity http) {

    return http.authorizeExchange()
        .pathMatchers("/health-check")
        .permitAll()
        .and()
        .authorizeExchange()
        .anyExchange()
        .authenticated()
        .and()
        .oauth2Login()
        .and()
        .build();
  }
}
