/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * Validation and response message related constant
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContMessage {

    public static final String SOMETHING_WENT_WRONG = "something.went.wrong";
    public static final String USER_CREATED = "user.created.successfully";
    public static final String VALIDATE_USER_CREATE_NAME_EXIST = "validate.user.create.name.exist";
}
