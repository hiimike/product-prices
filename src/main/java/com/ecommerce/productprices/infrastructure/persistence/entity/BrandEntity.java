package com.ecommerce.productprices.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "BRAND")
@Getter
@Setter
public class BrandEntity extends AbstractPersistable<Long> {

    @Column(name = "NAME", nullable = false)
    private String name;

}

