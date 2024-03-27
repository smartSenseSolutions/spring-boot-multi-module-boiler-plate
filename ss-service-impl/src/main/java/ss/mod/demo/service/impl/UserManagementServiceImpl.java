/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ss.mod.demo.api.model.request.FilterWrapper;
import ss.mod.demo.api.model.request.UserRequest;
import ss.mod.demo.api.model.response.PageResponse;
import ss.mod.demo.api.model.response.UserResponse;
import ss.mod.demo.dao.entity.UserMaster;
import ss.mod.demo.service.UserManagementService;
import ss.mod.demo.service.entity.BaseService;
import ss.mod.demo.service.entity.UserMasterService;

/**
 * User management service Implementation
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagementServiceImpl extends BaseService implements UserManagementService {

    private final UserMasterService userMasterService;

    @Override
    public UserResponse getUserById(String id) {
        return toType(userMasterService.get(id), UserResponse.class);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return toType(userMasterService.createUser(userRequest), UserResponse.class);
    }

    @Override
    public PageResponse<UserResponse> userFilter(FilterWrapper filterWrapper) {
        Page<UserMaster> filter = userMasterService.filter(filterWrapper);
        return toPageResponse(filter, filterWrapper, UserResponse.class);
    }
}
