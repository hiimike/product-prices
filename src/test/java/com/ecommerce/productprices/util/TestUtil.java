package com.ecommerce.productprices.util;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;

public class TestUtil {

    private static final Faker faker = new Faker();

    private TestUtil() {
    }

    public static LocalDateTime randomStartDate(Integer atMostDays) {
        return faker.date().past(atMostDays, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime randomEndDate(Integer atMostDays) {
        return faker.date().future(atMostDays, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime futureLocalDateTime(Integer atMostDays) {
        return faker.date().future(atMostDays, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }
}
