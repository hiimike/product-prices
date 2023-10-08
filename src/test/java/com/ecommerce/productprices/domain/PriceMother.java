package com.ecommerce.productprices.domain;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

import static com.ecommerce.productprices.util.TestUtil.randomEndDate;
import static com.ecommerce.productprices.util.TestUtil.randomStartDate;

public class PriceMother {

    private static final Faker faker = new Faker();

    private PriceMother() {
    }

    public static Price random() {
        return Price.builder()
                .brand(BrandMother.random())
                .startDate(randomStartDate(1))
                .endDate(randomEndDate(10))
                .priceList(faker.number().randomDigit())
                .productId(faker.number().randomNumber())
                .priority(faker.number().randomDigit())
                .value(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 1000)))
                .curr(faker.currency().code())
                .build();
    }
}
