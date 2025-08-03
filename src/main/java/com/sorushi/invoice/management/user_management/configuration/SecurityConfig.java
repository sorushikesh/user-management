package com.sorushi.invoice.management.user_management.configuration;

import com.sorushi.invoice.management.user_management.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;

  public SecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /** Copied from SecurityFilterChainConfiguration class and done custom implementation */
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

    // Disable CSRF for APIs / Postman
    http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

    http.authorizeHttpRequests(
        request -> request.requestMatchers("/api/v1/users/*").authenticated());

    /* If protected API end point only will be accessed programmatically by client then to disable its access to web client */
    /*    http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable()); */

    /* If protected API end point only will be accessed by web client then to disable its access for programmatically access */
    /*    http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable()); */

    /* Now setting both with default */
    http.formLogin(Customizer.withDefaults());
    http.httpBasic(Customizer.withDefaults());

    return http.build();
  }

  /*
  For InMemory database
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails admin = User.builder()
        .username("admin")
        .password("{noop}admin1234")
        .authorities("read")
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(admin);
  }
  */

  @Bean
  public AuthenticationManager authenticationManager(
      HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
      throws Exception {

    AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    return builder.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return userDetailsService;
  }
}
