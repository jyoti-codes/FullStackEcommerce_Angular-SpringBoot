package com.luv2code.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // CSRF performs checks on POST using cookies. Since cookies are not used we can disable CSRF or we get UnAuthorized
        //secure end points /api/orders so that products dont appear without login
        http.authorizeHttpRequests(configurer -> configurer.requestMatchers("/api/orders/**").authenticated().anyRequest().permitAll())
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults()));

        //add cors filters
        http.cors(withDefaults());
        //add content negotiation startegy
        http.setSharedObject(ContentNegotiationStrategy.class,new HeaderContentNegotiationStrategy());
        //non empty response for 401
        Okta.configureResourceServer401ResponseBody(http);
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
