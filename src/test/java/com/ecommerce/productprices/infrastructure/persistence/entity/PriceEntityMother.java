package com.ecommerce.productprices.infrastructure.persistence.entity;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

import static com.ecommerce.productprices.util.TestUtil.randomEndDate;
import static com.ecommerce.productprices.util.TestUtil.randomStartDate;

public class PriceEntityMother {

    private static final Faker faker = new Faker();

    private PriceEntityMother() {
    }

    public static PriceEntity random() {
        return PriceEntity.builder()
                .brand(BrandEntityMother.random())
                .startDate(randomStartDate(1))
                .endDate(randomEndDate(10))
                .priceList(faker.number().randomDigit())
                .productId(faker.number().randomNumber())
                .priority(faker.number().randomDigit())
                .price(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 1000)))
                .currency(faker.currency().code())
                .build();
    }

}
