/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.api.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import ss.mod.demo.api.model.BaseModel;

/**
 * User request
 *
 * @param name    name of user
 * @param age     age of user
 * @param city    city of user
 * @param country country of city
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public record UserRequest(@NotEmpty(message = "{please.enter.user.name}") String name,
                          @Min(value = 18, message = "{you.are.under.age}") Integer age,
                          @NotEmpty(message = "{please.enter.city}") String city,
                          @NotEmpty(message = "{please.enter.county}") String country) implements BaseModel {
}
