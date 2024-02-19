package ss.mod.demo.util;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static ss.mod.demo.util.constant.KeycloakTestConstant.BEARER;
import static ss.mod.demo.util.constant.KeycloakTestConstant.INVALID_USER_NAME;
import static ss.mod.demo.util.constant.KeycloakTestConstant.KEYCLOAK_ADMIN_USERNAME;
import static ss.mod.demo.util.constant.KeycloakTestConstant.KEYCLOAK_PRIVATE_CLIENT;
import static ss.mod.demo.util.constant.KeycloakTestConstant.KEYCLOAK_PUBLIC_CLIENT;
import static ss.mod.demo.util.constant.KeycloakTestConstant.KEYCLOAK_REALM;
import static ss.mod.demo.util.constant.KeycloakTestConstant.PASSWORD;
import static ss.mod.demo.util.constant.KeycloakTestConstant.REAL_FILE_PATH;
import static ss.mod.demo.util.constant.KeycloakTestConstant.VALID_USER_NAME;


public class ContainerContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:15.2");
    private static final KeycloakContainer keycloak = new KeycloakContainer("jboss/keycloak:16.1.1")
            .withRealmImportFile(REAL_FILE_PATH)
            .withAdminPassword(KEYCLOAK_ADMIN_USERNAME)
            .withAdminPassword(PASSWORD);

    static String createToken(boolean validUser) {

        KeycloakBuilder keycloakBuilder = KeycloakBuilder.builder()
                .serverUrl(ContainerContextInitializer.keycloak.getAuthServerUrl())
                .realm(KEYCLOAK_REALM)
                .clientId(KEYCLOAK_PUBLIC_CLIENT)
                .username(INVALID_USER_NAME)
                .password(PASSWORD);
        if (validUser) {
            keycloakBuilder.username(VALID_USER_NAME);
        }
        String access_token = keycloakBuilder.build().tokenManager().getAccessToken().getToken();
        return BEARER + access_token;
        
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ContainerContextInitializer.postgreSQLContainer.start();
        TestPropertyValues testPropertyValues = TestPropertyValues.of(
                "spring.datasource.url=" + ContainerContextInitializer.postgreSQLContainer.getJdbcUrl() + "&currentSchema=public",
                "spring.datasource.username=" + ContainerContextInitializer.postgreSQLContainer.getUsername(),
                "spring.datasource.password=" + ContainerContextInitializer.postgreSQLContainer.getPassword()
        );

        boolean keycloakContainer = Boolean.valueOf(applicationContext.getEnvironment().getProperty("app.container.keycloak"));
        if (keycloakContainer) {
            ContainerContextInitializer.keycloak.withStartupTimeout(Duration.of(5, ChronoUnit.MINUTES));
            ContainerContextInitializer.keycloak.start();

            testPropertyValues = testPropertyValues.and("app.security.auth-server-url=" + ContainerContextInitializer.keycloak.getAuthServerUrl(),
                    "app.security.realm=" + KEYCLOAK_REALM,
                    "app.security.clientId=" + KEYCLOAK_PRIVATE_CLIENT);
        }
        testPropertyValues.applyTo(applicationContext.getEnvironment());
    }
}
