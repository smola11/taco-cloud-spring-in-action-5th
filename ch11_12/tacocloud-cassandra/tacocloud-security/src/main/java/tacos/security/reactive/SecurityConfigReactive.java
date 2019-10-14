package tacos.security.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

// EXAMPLE OF SIMPLE CONFIGURATION OF SPRING SECURITY FOR REACTIVE SPRING WEBFLUX APPLICATION

@Configuration
@EnableWebFluxSecurity
public class SecurityConfigReactive {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
            .authorizeExchange()
            .pathMatchers("/design", "/orders").hasAuthority("USER")
            .anyExchange().permitAll()
            .and()
            .build();
    }
}
