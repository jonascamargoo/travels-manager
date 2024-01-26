package br.com.jonascamargo.travelsmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.jonascamargo.travelsmanager.services.security.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    // validando se o usuario esta apto a fazer a requisicao - declarando corrente
    // de filtros de auth

    @Autowired
    private SecurityFilter securityFilter;

    // public SecurityConfiguration(SecurityFilter securityFilter) {
    //     this.securityFilter = securityFilter;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // desligando config
                .csrf(csrf -> csrf.disable())
                // auth via stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // permitindo qualquer usuario a TENTAR fazer o login (obviamente nao estara autenticado)
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        // apena para teste. Posteriormente devo retirar essa permissao e criar manualmente um usuario 0 no banco de dados, e a partir dele criar o restante
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()

                        // users
                        //.requestMatchers(HttpMethod.GET, "/api/")

                        // tickets
                        .requestMatchers(HttpMethod.GET, "/api/tickets/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/tickets/").permitAll()

                        // places
                        .requestMatchers(HttpMethod.GET, "/api/places/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/places").hasRole("ADMIN")

                        // passengers
                        .requestMatchers(HttpMethod.GET, "/api/passengers/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/passengers/").permitAll()

                        // payments
                        .requestMatchers(HttpMethod.GET, "/api/payments/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/payments/").permitAll()

                        // qualquer outro metodo em qualquer um endpoint devera passar por autenticacao
                        .anyRequest().authenticated()
                )
                // primeiro o metodo passara pelo securityFilter - onde tera as credenciais em contexto ou nao - e quando chegar em UsernamePasswordAuthenticationFilter.class e nao encontrar nada, retornara o 403 
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}