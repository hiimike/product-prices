package com.ecommerce.productprices.infrastructure.persistence.mapper.brand;

import com.ecommerce.productprices.domain.Brand;
import com.ecommerce.productprices.infrastructure.persistence.entity.BrandEntity;

public interface BrandMapper {

    Brand toDomain(BrandEntity entity);

    BrandEntity toEntity(Brand domain);
}
