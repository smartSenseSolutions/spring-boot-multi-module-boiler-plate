/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.web.resource;

import com.smartsensesolutions.commons.dao.filter.FilterRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ss.mod.demo.api.constant.ContField;
import ss.mod.demo.api.constant.ContMessage;
import ss.mod.demo.api.constant.ContURI;
import ss.mod.demo.api.model.request.UserRequest;
import ss.mod.demo.api.model.response.PageResponse;
import ss.mod.demo.api.model.response.ResponseBody;
import ss.mod.demo.api.model.response.UserResponse;
import ss.mod.demo.service.UserManagementService;
import ss.mod.demo.web.apidocs.UserManagementResourceApiDocs.Common500;
import ss.mod.demo.web.apidocs.UserManagementResourceApiDocs.CreateUserApiDocs;

/**
 * Provide endpoint related to User management
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@AllArgsConstructor
@RestController
@Slf4j
@Common500
public class UserManagementResource extends BaseResource {
    private final UserManagementService userManagementService;

    @CreateUserApiDocs
    @PostMapping(value = ContURI.USER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBody<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse user = userManagementService.createUser(userRequest);
        return ResponseBody.of(resolveMessage(ContMessage.USER_CREATED), user);
    }

    @Operation(summary = "Get User")
    @GetMapping(value = ContURI.USER_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUser(@PathVariable(ContField.USER_ID) String userId) {
        return userManagementService.getUserById(userId);
    }

    @Operation(summary = "User Filter")
    @PostMapping(value = ContURI.USER_FILTER, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<UserResponse> userFilter(@Valid @RequestBody FilterRequest filterRequest) {
        return userManagementService.userFilter(filterRequest);
    }
}
