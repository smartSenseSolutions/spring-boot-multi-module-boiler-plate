package ss.mod.demo.util.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeycloakTestConstant {
    public static final String BEARER = "Bearer ";
    public static final String REAL_FILE_PATH = "app-test-realm.json";
    public static final String KEYCLOAK_ADMIN_USERNAME = "admin";
    public static final String KEYCLOAK_REALM = "SWD";
    public static final String KEYCLOAK_PUBLIC_CLIENT = "pb_backend";
    public static final String KEYCLOAK_PRIVATE_CLIENT = "pv_backend";
    public static final String VALID_USER_NAME = "valid-user";
    public static final String INVALID_USER_NAME = "invalid-user";
    public static final String PASSWORD = "password"; //pragma: allowlist secret

}
