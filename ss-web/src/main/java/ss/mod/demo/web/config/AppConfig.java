/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import ss.mod.demo.service.entity.AuditorAwareImpl;
import ss.mod.demo.web.config.feign.FeignProperties;
import ss.mod.demo.web.config.feign.retry.FeignRetryer;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ApplicationConfig contains the configuration bean that will be used by the application.
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Configuration
public class AppConfig {
    @Value("${messages.bundle.path}")
    private String resourceBundlePath;

    private final FeignProperties feignProperties;


    @Autowired
    public AppConfig(FeignProperties feignProperties) {
        this.feignProperties = feignProperties;
    }

    /**
     * ObjectMapper bean that will be used for serialization and deserialization of the Json object.
     *
     * @return {@link ObjectMapper}
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false)
                .registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    /**
     * Bean used for managing the I18n Support to the application.
     * Each messages will be load by the messageSource.
     *
     * @return {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(resourceBundlePath);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public Retryer feignRetryer() {
        return new FeignRetryer(feignProperties.getMaxRetryAttempt(), feignProperties.getRetryInterval());
    }

    @Bean
    public ExecutorService getExecutorService() {
        return Executors.newFixedThreadPool(5);
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
