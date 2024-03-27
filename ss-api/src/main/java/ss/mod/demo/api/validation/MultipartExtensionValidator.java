/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@code MultipartExtensionValidator} is validator class for validating extension of uploaded file/multipart file.
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public class MultipartExtensionValidator implements ConstraintValidator<MultipartFileExtension, MultipartFile> {

    /**
     * String array of allowed extensions
     */
    private String[] extensions;

    @Override
    public void initialize(MultipartFileExtension constraintAnnotation) {
        extensions = constraintAnnotation.extension();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        boolean isValid = false;
        if (value != null) {
            for (String extension : extensions) {
                if (value.getOriginalFilename() != null && value.getOriginalFilename().toLowerCase().endsWith(extension.toLowerCase())) {
                    return true;
                }
            }
        } else {
            isValid = true;
        }
        return isValid;
    }
}
