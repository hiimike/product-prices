package com.ecommerce.productprices.infrastructure.persistence.mapper.price;

import com.ecommerce.productprices.domain.Brand;
import com.ecommerce.productprices.domain.BrandMother;
import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntityMother;
import com.ecommerce.productprices.infrastructure.persistence.mapper.brand.BrandMapper;
import com.ecommerce.productprices.infrastructure.persistence.mapper.brand.BrandMapperH2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PriceMapperH2Test {

    private BrandMapper brandMapperH2;
    private PriceMapper priceMapper;

    @BeforeEach
    void setUp() {
        brandMapperH2 = Mockito.mock(BrandMapperH2.class);
        priceMapper = new PriceMapperH2(brandMapperH2);
    }

    @Test
    void testToDomain() {
        // Given
        PriceEntity priceEntity = PriceEntityMother.random();
        Brand brand = BrandMother.random();
        Price expected = Price.builder()
                .brand(brand)
                .productId(priceEntity.getProductId())
                .priceList(priceEntity.getPriceList())
                .value(priceEntity.getPrice())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .curr(priceEntity.getCurrency())
                .priority(priceEntity.getPriority())
                .build();
        Mockito.doReturn(brand).when(brandMapperH2).toDomain(priceEntity.getBrand());


        // When
        Price actual = priceMapper.toDomain(priceEntity);

        // Then
        Assertions.assertEquals(expected, actual);
    }
}
