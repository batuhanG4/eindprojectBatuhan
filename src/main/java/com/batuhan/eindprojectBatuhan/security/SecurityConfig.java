package com.batuhan.eindprojectBatuhan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/register", "/login").permitAll() // Openbare pagina's
                        .anyRequest().authenticated() // Beveilig alle andere pagina's
                )
                .formLogin(form -> form
                        .loginPage("/login") // URL voor loginpagina
                        .defaultSuccessUrl("/", true) // URL na succesvolle login
                        .permitAll() // Loginpagina is toegankelijk voor iedereen
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll() // Logoutpagina is toegankelijk voor iedereen
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
