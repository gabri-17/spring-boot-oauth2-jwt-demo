package com.devsuperior.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configuração de controle de acesso global da aplicação.
@Configuration
public class SecurityConfig {

    // Classe onde se é possível definir componentes na forma de metodo.
    @Bean // Nome do metodo não importa.
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(); // Instancia o objeto que faz o hash da senha que vai virar um componente
        // que pode ser injetado agora em outros lugares (com a annotation @Bean).
    }

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
