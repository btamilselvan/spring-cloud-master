package com.success.security.config;

import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

    return http.csrf(c -> c.disable()).authorizeHttpRequests(e -> e.anyRequest().authenticated())
        .oauth2ResourceServer(
            customizer -> customizer.jwt(c -> c.jwtAuthenticationConverter(jwtConverter())))
        .build();
  }

  public JwtAuthenticationConverter jwtConverter() {
    var jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> jwt.getClaimAsStringList("custom_claims")
        .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    return jwtConverter;
  }
}
