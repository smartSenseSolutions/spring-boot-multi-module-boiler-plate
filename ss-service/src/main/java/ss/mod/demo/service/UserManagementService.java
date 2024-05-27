/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.service;

import com.smartsensesolutions.commons.dao.filter.FilterRequest;
import ss.mod.demo.api.model.request.UserRequest;
import ss.mod.demo.api.model.response.PageResponse;
import ss.mod.demo.api.model.response.UserResponse;

/**
 * Abstract User management service
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
public interface UserManagementService {
    UserResponse getUserById(String id);

    UserResponse createUser(UserRequest userRequest);

    PageResponse<UserResponse> userFilter(FilterRequest filterRequest);
}
