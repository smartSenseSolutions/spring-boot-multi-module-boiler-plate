/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.dao.repository;

import com.smartsensesolutions.commons.dao.base.BaseRepository;
import org.springframework.stereotype.Repository;
import ss.mod.demo.dao.entity.UserMaster;

/**
 * Database operation related to {@link UserMaster}
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@Repository
public interface UserMasterRepository extends BaseRepository<UserMaster, String> {
    boolean existsByName(String name);
}
