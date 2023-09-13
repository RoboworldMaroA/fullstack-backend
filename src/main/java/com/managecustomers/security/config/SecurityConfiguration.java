package com.managecustomers.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**",("/displayUsers"),("/displayUsers/**")).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }


      @Bean
      CorsConfigurationSource corsConfigurationSource(){
          CorsConfiguration configuration = new CorsConfiguration();

          String allowsOrigin = "http://127.0.0.1:5501";
          String allowsOrigin2 = "https://manage-customers-4x6n9.ondigitalocean.app";
          configuration.setAllowedOrigins(List.of(allowsOrigin,allowsOrigin2));
          configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT", "HEAD", "OPTIONS","DELETE"));
          configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","accept", "accept-language", "content-type",  "authorization", "moduleid", "tabid", "x-dnn-moniker"));

          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**",configuration);
          return source;
      }



}
