/*
 * Copyright (c) 2024-25 Smart Sense Consulting Solutions Pvt. Ltd.
 */
package ss.mod.demo.dao.entity;

import com.smartsensesolutions.java.commons.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base auditable entity for common audit details
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity implements BaseEntity {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate;
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

}
