package com.project.hirehive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.project.hirehive.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
            .requestMatchers("/apply").authenticated()
            .requestMatchers("/uploads/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        );

    return http.build();
}


}

