package br.com.petweb.petweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                        throws Exception {

                http
                                .csrf(csrf -> csrf.disable())

                                .authorizeHttpRequests(auth -> auth

                                                // =========================
                                                // ACESSO PÚBLICO
                                                // =========================

                                                .requestMatchers(
                                                                "/login",
                                                                "/petweb",
                                                                "/css/**",
                                                                "/img/**",
                                                                "/js/**",
                                                                "/favicon.ico",
                                                                "/usuarios/criar",
                                                                "/usuarios/salvar")
                                                .permitAll()

                                                // =========================
                                                // SOMENTE ADMIN
                                                // =========================

                                                .requestMatchers(
                                                                "/produtos/**")
                                                .hasRole("ADMIN")

                                                .requestMatchers(
                                                                "/animais/consulta/**")
                                                .hasRole("ADMIN")

                                                // =========================
                                                // USER + ADMIN
                                                // =========================

                                                .requestMatchers(
                                                                "/clientes/**",
                                                                "/animais/**",
                                                                "/veterinarios/**",
                                                                "/consultas/**",
                                                                "/pedidos/**")
                                                .hasAnyRole("USER", "ADMIN")

                                                // =========================
                                                // RESTANTE
                                                // =========================

                                                .anyRequest().authenticated())

                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())

                                .logout(logout -> logout
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration config)
                        throws Exception {

                return config.getAuthenticationManager();
        }
}