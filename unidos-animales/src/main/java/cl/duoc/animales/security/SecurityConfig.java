package cl.duoc.animales.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import jakarta.servlet.SessionTrackingMode;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bloqueo del JSESSIONID en la URL (Para ZAP)
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
    }

    // 1. EL ENCRIPTADOR DE CONTRASEÑAS 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. El administrador de autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 3. Las reglas de acceso y protección
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'; script-src 'self'; style-src 'self'; img-src 'self' data:; font-src 'self'; form-action 'self'; frame-ancestors 'none';"))
            )
            .authorizeHttpRequests((requests) -> requests
                
                .dispatcherTypeMatchers(jakarta.servlet.DispatcherType.ERROR).permitAll() 
                .requestMatchers("/", "/publica", "/login", "/enviar-solicitud", "/api/auth/**", "/css/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/privada", true)
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll());

        return http.build();
    }
}