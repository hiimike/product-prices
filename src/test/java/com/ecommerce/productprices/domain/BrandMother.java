package com.ecommerce.productprices.domain;

import com.github.javafaker.Faker;

public class BrandMother {

    private static final Faker faker = new Faker();

    private BrandMother() {
    }

    public static Brand random() {
        return Brand.builder()
                .name(faker.company().name())
                .build();
    }
}
