package com.apiestoque.produto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ CSRF (API REST)
            .csrf(csrf -> csrf.disable())

            // ❌ Autenticações padrão
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable())

            // ✅ API 100% STATELESS (JWT)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ JWT FILTER
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            )

            // ✅ AUTORIZAÇÕES
            .authorizeHttpRequests(auth -> auth

                // 🔓 AUTH + SWAGGER (PÚBLICOS)
                .requestMatchers(
                    "/auth/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()

                // 🔐 PRODUTOS - GET
                .requestMatchers(HttpMethod.GET, "/produtos/**")
                    .hasAnyRole("USER", "ADMIN")

                // 🔐 PRODUTOS - CRUD
                .requestMatchers(HttpMethod.POST, "/produtos/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/produtos/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/produtos/**")
                    .hasRole("ADMIN")

                // 🔐 QUALQUER OUTRA ROTA
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
