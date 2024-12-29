package com.batuhan.eindprojectBatuhan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Zorg ervoor dat BCryptPasswordEncoder beschikbaar is als bean
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/register", "/login", "/h2-console/**").permitAll() // Openbare pagina's incl. H2-console
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
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Schakel CSRF uit voor H2-console
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()) // Zorg ervoor dat frame-options correct zijn ingesteld
                );

        return http.build();
    }

    // Define BCryptPasswordEncoder bean here

}
