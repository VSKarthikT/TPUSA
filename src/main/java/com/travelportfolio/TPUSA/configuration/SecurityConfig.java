package com.travelportfolio.TPUSA.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private JWTFilter jwtFilter;
  @Autowired
  private CustomAuthenticationEntryPoint CustomAuthenticationEntryPoint;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(customizer -> customizer.disable())
        .authorizeHttpRequests(request -> request
            .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register", "/", "/api/v1/places/states/**")
            .permitAll()
            .requestMatchers("api/admin/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers("/api/v1/user/**").authenticated()
            .anyRequest().authenticated())
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(CustomAuthenticationEntryPoint))
        .httpBasic(Customizer.withDefaults())
        .cors(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  // This authenication provider is used to authenticate the user it needs to
  // connect to DB to get the user details
  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
