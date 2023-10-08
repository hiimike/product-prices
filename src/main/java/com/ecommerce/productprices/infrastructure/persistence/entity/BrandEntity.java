package com.ecommerce.productprices.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BRAND")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq")
    @SequenceGenerator(name = "brand_seq", sequenceName = "BRAND_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

}

