/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.resource;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ss.mod.demo.api.constant.ContField;
import ss.mod.demo.api.constant.ContMessage;
import ss.mod.demo.api.constant.ContURI;
import ss.mod.demo.api.model.request.FilterWrapper;
import ss.mod.demo.api.model.request.UserRequest;
import ss.mod.demo.api.model.response.BodyResponse;
import ss.mod.demo.api.model.response.PageResponse;
import ss.mod.demo.api.model.response.UserResponse;
import ss.mod.demo.service.UserManagementService;

/**
 * Provide endpoint related to User management
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@AllArgsConstructor
@RestController
@Slf4j
public class UserManagementResource extends BaseResource {
    private final UserManagementService userManagementService;

    @Operation(summary = "Create User")
    @PostMapping(value = ContURI.USER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BodyResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse user = userManagementService.createUser(userRequest);
        return BodyResponse.of(resolveMessage(ContMessage.USER_CREATED), user);
    }

    @Operation(summary = "Get User")
    @GetMapping(value = ContURI.USER_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUser(@PathVariable(ContField.USER_ID) String userId) {
        return userManagementService.getUserById(userId);
    }

    @Operation(summary = "User Filter")
    @PostMapping(value = ContURI.USER_FILTER, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<UserResponse> userFilter(@Valid @RequestBody FilterWrapper filterWrapper) {
        return userManagementService.userFilter(filterWrapper);
    }
}
