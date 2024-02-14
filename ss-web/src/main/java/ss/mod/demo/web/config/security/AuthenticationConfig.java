/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ss.mod.demo.api.constant.ContURI;
import ss.mod.demo.api.constant.ContValue;
import ss.mod.demo.service.entity.AuditorAwareImpl;

import java.util.List;

/**
 * AuthenticationConfig provided the security to the endpoint.
 * Each and every call first authenticate from here and after that will go to the controller level.
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Slf4j
@EnableWebSecurity
@Configuration
@AllArgsConstructor
@EnableMethodSecurity()
public class AuthenticationConfig {

    private final SecurityConfigProperties configProperties;

    /**
     * This is a conditional bean which will be created if app.security.enabled= true.
     * This contains the basic role authentication mechanism for the endpoint that we introduced at controller layer.
     *
     * @param http - Indicate HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception - Indicates the exception
     */
    @Bean
    @ConditionalOnProperty(value = "app.security.enabled", havingValue = "true", matchIfMissing = true)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_ALL, HttpMethod.OPTIONS.name())).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_API_DOCS_ALL)).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_SWAGGER_IU_ALL)).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_HEALTH_ALL)).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_PRIVATE_ALL)).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_AUTHENTICATED_ALL)).hasRole("<UserRole>");
                    auth.requestMatchers(new AntPathRequestMatcher(ContURI.CONTEXT_AUTHENTICATED_WITH_NO_FILTER_ALL)).hasRole("<UserRole>");
                }).oauth2ResourceServer(oath -> oath.jwt(jwt -> jwt.jwtAuthenticationConverter(new CustomAuthenticationConverter(configProperties.roleClientId()))));
        return http.build();
    }


    /**
     * This is a conditional bean which will be created if app.security.enabled= false.
     * This is not recommended configuration that will use in production environments.
     *
     * @return {@link WebSecurityCustomizer} - Indicates web security customizer
     */
    @Bean
    @ConditionalOnProperty(value = "app.security.enabled", havingValue = "false")
    public WebSecurityCustomizer securityCustomizer() {
        AuthenticationConfig.log.warn("Disable security : This is not recommended to use in production environments.");
        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("**"));
    }

    /**
     * This is a Bean which will be manage the cors configuration.
     *
     * @return {@link CorsConfigurationSource}
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(configProperties.corsOrigins());
        configuration.setAllowedMethods(List.of(HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(), HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
        configuration.setAllowedHeaders(List.of(ContValue.ALLOWED_HEADERS));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(ContURI.CONTEXT_ALL, configuration);
        return source;
    }

    /**
     * This bean will responsible to maintain auditing field on entity
     *
     * @return {@link AuditorAware}
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}

