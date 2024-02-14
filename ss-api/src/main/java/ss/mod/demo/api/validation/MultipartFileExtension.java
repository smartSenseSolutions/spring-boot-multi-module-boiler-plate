/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * {@code MultipartFileExtension} annotation is for validation of extension of the file.
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Documented
@Constraint(validatedBy = {MultipartExtensionValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartFileExtension {

    /**
     * Validation Message
     *
     * @return validation message
     */
    String message() default "Invalid file extension";

    /**
     * Valid extensions of the uploaded file
     *
     * @return file extensions
     */
    String[] extension();

    /**
     * @return class
     */
    Class<?>[] groups() default {};

    /**
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};
}