package com.ecommerce.productprices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProductPricesApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProductPricesApplication::main).with(TestProductPricesApplication.class).run(args);
    }

}
