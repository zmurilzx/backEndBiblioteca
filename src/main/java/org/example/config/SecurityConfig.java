package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa CSRF (não precisa em APIs REST)
                .csrf(csrf -> csrf.disable())
                // Habilita o CORS global
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Libera todas as rotas (modo desenvolvimento)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // Desativa autenticação básica (opcional)
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permite acesso do Angular local
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        // Permite todos os métodos HTTP
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permite todos os headers
        configuration.setAllowedHeaders(List.of("*"));
        // Permite envio de cookies/credenciais (se precisar)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
