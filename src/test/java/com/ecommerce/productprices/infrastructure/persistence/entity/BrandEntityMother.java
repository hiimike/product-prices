package com.ecommerce.productprices.infrastructure.persistence.entity;

import com.github.javafaker.Faker;

public class BrandEntityMother {

    private static final Faker faker = new Faker();

    private BrandEntityMother() {
    }

    public static BrandEntity random() {
        return BrandEntity.builder()
                .name(faker.name().name())
                .build();
    }
}
