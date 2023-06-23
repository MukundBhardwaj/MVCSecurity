package com.mukund.mvcsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;

@Configuration
/**
 * Security Config class for defining beans customizing Spring Security
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class SecurityConfig {

    @Bean
    /**
     * Bean method to get default encoder for passwords
     * 
     * @return {@code PasswordEncoder} default password encoder object
     */
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    /**
     * Bean method to get {@code AuthenticationProvider}
     * 
     * @param userDetailsService {@code UserDetailsService} Service Object used for
     *                           Spring Security
     * @return {@code AuthenticationProvider} default authentication provider object
     */
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(getEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    /**
     * Bean method for {@code SecurityFilterChain}
     * 
     * @param http {@code HttpSecurity} Spring Security http security object
     * @return {@code SecurityFilterChain}
     * @throws Exception
     */
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginProcessingUrl("/signin").defaultSuccessUrl("/", true))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.deny())
                        .httpStrictTransportSecurity(
                                hstsConfig -> hstsConfig.includeSubDomains(true).maxAgeInSeconds(315536000))
                        .xssProtection(
                                xssProtectionConfig -> xssProtectionConfig.headerValue(HeaderValue.ENABLED_MODE_BLOCK))
                        .contentSecurityPolicy(
                                contentSecurityPolicy -> contentSecurityPolicy.policyDirectives("default-src 'self';")))
                .cors(corsConfig -> corsConfig.disable())
                .build();
    }
}