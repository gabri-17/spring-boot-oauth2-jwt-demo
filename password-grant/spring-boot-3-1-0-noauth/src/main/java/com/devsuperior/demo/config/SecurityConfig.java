package com.devsuperior.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
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
    @Profile("test") // Filtro para ativar esse bean apenas quando o profile "test" estiver ativo.
    @Order(1)
    SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher(PathRequest.toH2Console()).csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // Desabilita proteção CSRF (ataque quando se tem
        // uma aplicação que grava dados da seção -> como back-end é uma API REST é como não guarda dados de seção, não
        // é preciso se preocupar) para facilitar testes com Postman ou Insomnia.

        // Configura as autorizações de requisições HTTP (endpoints).
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
