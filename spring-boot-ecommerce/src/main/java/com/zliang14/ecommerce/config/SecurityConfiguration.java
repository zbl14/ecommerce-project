package com.zliang14.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // protect endpoint /api/orders
    http.authorizeHttpRequests(configurer -> {
      configurer.requestMatchers("/api/orders/**").authenticated().requestMatchers("/**").permitAll();
      ;
    }).oauth2ResourceServer().jwt();

    // add CORS filters
    http.cors();

    // add content negotitation strategy to support Okta sending back response
    http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

    // force a non-empty response body for 401
    Okta.configureResourceServer401ResponseBody(http);

    return http.build();
  }
}
