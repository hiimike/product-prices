package com.ecommerce.productprices.infrastructure.persistence.mapper.brand;

import com.ecommerce.productprices.domain.Brand;
import com.ecommerce.productprices.infrastructure.persistence.entity.BrandEntity;
import com.ecommerce.productprices.infrastructure.persistence.entity.BrandEntityMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrandMapperH2Test {

    private BrandMapper brandMapper;

    @BeforeEach
    void setUp() {
        brandMapper = new BrandMapperH2();
    }

    @Test
    void testToDomain() {
        // Given
        BrandEntity brandEntity = BrandEntityMother.random();
        Brand expected = Brand.builder()
                .name(brandEntity.getName())
                .build();
        // When
        Brand actual = brandMapper.toDomain(brandEntity);

        // Then
        Assertions.assertEquals(expected, actual);
    }
}
