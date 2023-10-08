package com.ecommerce.productprices.infrastructure.persistence.mapper.brand;

import com.ecommerce.productprices.domain.Brand;
import com.ecommerce.productprices.infrastructure.persistence.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public class BrandMapperH2 implements BrandMapper {

    @Override
    public Brand toDomain(BrandEntity entity) {
        return Brand.builder()
                .name(entity.getName())
                .build();
    }

    @Override
    public BrandEntity toEntity(Brand domain) {
        return BrandEntity.builder()
                .name(domain.name())
                .build();
    }
}
