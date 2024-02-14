/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ss.mod.demo.web.config.appinfo.InfoConfiguration;
import ss.mod.demo.web.config.security.SecurityConfigProperties;

import java.util.Collections;

/**
 * Open Api Config
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Configuration
@AllArgsConstructor
public class OpenApiConfig {

    private final SecurityConfigProperties properties;
    private final InfoConfiguration appInfoConfiguration;

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info();
        info.setTitle(appInfoConfiguration.name());
        info.setDescription(appInfoConfiguration.description());
        info.setVersion(appInfoConfiguration.version());
        OpenAPI openAPI = new OpenAPI();
        if (properties.enabled()) {
            openAPI = enableSecurity(openAPI);
        }
        return openAPI.info(info);
    }

    @Bean
    public GroupedOpenApi openApiDefinition() {
        return GroupedOpenApi.builder()
                .group("docs")
                .pathsToMatch("/**")
                .displayName("Docs")
                .build();
    }

    private OpenAPI enableSecurity(OpenAPI openAPI) {
        Components components = new Components();
        components.addSecuritySchemes(
                "open_id_scheme",
                new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .flows(new OAuthFlows()
                                .authorizationCode(new OAuthFlow()
                                        .authorizationUrl(properties.authUrl())
                                        .tokenUrl(properties.tokenUrl())
                                        .refreshUrl(properties.refreshTokenUrl()
                                        )
                                )
                        )
        );
        return openAPI.components(components)
                .addSecurityItem(new SecurityRequirement().addList("open_id_scheme", Collections.emptyList()));
    }
}
