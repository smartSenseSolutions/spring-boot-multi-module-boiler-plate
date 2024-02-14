/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * All URL and partial URL constant
 *
 * @author Sunil Kanzar
 * @since 20th of Dec 2023
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContURI {
    //Test Resource
    public static final String USER = "/common/user";
    public static final String USER_WITH_ID = "/common/user/{userId}";
    public static final String USER_FILTER = "/common/user/filter";

    //Context
    public static final String CONTEXT_AUTHENTICATED_ALL = "/common/**";
    public static final String CONTEXT_AUTHENTICATED_WITH_NO_FILTER_ALL = "/common2/**";
    public static final String CONTEXT_PRIVATE_ALL = "/private/**";
    public static final String CONTEXT_HEALTH_ALL = "/actuator/health/**";
    public static final String CONTEXT_SWAGGER_IU_ALL = "/ui/swagger-ui/**";
    public static final String CONTEXT_API_DOCS_ALL = "/docs/api-docs/**";
    public static final String CONTEXT_ALL = "/**";
}
