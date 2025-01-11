package com.batuhan.eindprojectBatuhan.security;

import com.batuhan.eindprojectBatuhan.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // CustomUserDetailsService wordt gebruikt voor het ophalen van gebruikersgegevens
    private final CustomUserDetailsService userDetailsService;

    // Constructor om CustomUserDetailsService in te stellen
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Beveiligingsmechanisme voor het versleutelen van wachtwoorden met BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Geeft een BCryptPasswordEncoder terug die wachtwoorden versleutelt
    }

    // AuthenticationProvider instellen voor het authenticeren van gebruikers via CustomUserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);  // Gebruikerdetails ophalen via de custom service
        authProvider.setPasswordEncoder(passwordEncoder());  // Wachtwoorden versleutelen met BCrypt
        return authProvider;
    }

    // Instellen van AuthenticationManager voor het verwerken van authenticatie
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();  // Retourneert het AuthenticationManager object
    }

    // Beveiliging configureren voor HTTP-verzoeken, login en logout
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth  // Autorisatie-instellingen voor HTTP-verzoeken
                        .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()  // Geef toegang zonder inloggen voor bepaalde URL's
                        .anyRequest().authenticated()  // Vereist authenticatie voor alle andere verzoeken
                )
                .formLogin(form -> form  // Configuratie van de login-pagina
                        .loginPage("/login")  // Specifieer de login pagina
                        .defaultSuccessUrl("/home", true)  // Redirect naar '/home' na succesvolle login
                        .permitAll()  // Sta iedereen toe om de login-pagina te bekijken
                )
                .logout(logout -> logout  // Configuratie van de logout-functionaliteit
                        .logoutUrl("/logout")  // Logout URL
                        .logoutSuccessUrl("/login")  // Redirect naar login na uitloggen
                )
                .csrf(csrf -> csrf.disable()); // CSRF uitschakelen indien noodzakelijk

        return http.build();  // Bouw de beveiligingsinstellingen en retourneer
    }
}
