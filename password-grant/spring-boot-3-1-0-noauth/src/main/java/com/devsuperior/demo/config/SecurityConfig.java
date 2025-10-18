package com.devsuperior.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // Configuração de controle de acesso global da aplicação.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // Desabilita proteção CSRF (ataque quando se tem
        // uma aplicação que grava dados da seção -> como back-end é uma API REST é como não guarda dados de seção, não
        // é preciso se preocupar) para facilitar testes com Postman ou Insomnia.

        // Configura as autorizações de requisições HTTP (endpoints).
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
