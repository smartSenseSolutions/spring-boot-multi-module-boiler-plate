/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config;

import lombok.AllArgsConstructor;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ss.mod.demo.api.constant.ContURI;
import ss.mod.demo.api.constant.ContValue;

/**
 * Web MVC Config
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@AllArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final SwaggerUiConfigProperties properties;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String redirectUri = properties.getPath();
        registry.addRedirectViewController(ContValue.FORWARD_SLASH, redirectUri);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns(ContURI.CONTEXT_AUTHENTICATED_ALL);
    }
}
