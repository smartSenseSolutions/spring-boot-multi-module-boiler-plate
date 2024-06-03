/*
 * Copyright 2024 smartSense Consulting Solutions Pvt. Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ss.mod.demo.service.entity;

import com.smartsensesolutions.commons.dao.base.BaseRepository;
import com.smartsensesolutions.commons.dao.specification.SpecificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ss.mod.demo.api.constant.ContMessage;
import ss.mod.demo.api.model.request.UserRequest;
import ss.mod.demo.api.utils.Validate;
import ss.mod.demo.dao.entity.UserMaster;
import ss.mod.demo.dao.repository.UserMasterRepository;

/**
 * User master entity Service
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */

@Service
public class UserMasterService extends BaseEntityService<UserMaster, String> {

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private SpecificationUtil<UserMaster> specificationUtil;

    public UserMaster createUser(UserRequest userRequest) {
        Validate.isTrue(userMasterRepository.existsByName(userRequest.name())).launch(ContMessage.VALIDATE_USER_CREATE_NAME_EXIST);
        return create(toType(userRequest, UserMaster.class));
    }

    @Override
    protected BaseRepository<UserMaster, String> getRepository() {
        return userMasterRepository;
    }

    @Override
    protected SpecificationUtil<UserMaster> getSpecificationUtil() {
        return specificationUtil;
    }
}
