package com.ecommerce.productprices.infrastructure.persistence.mapper.price;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;

public interface PriceMapper {

    Price toDomain(PriceEntity entity);

    PriceEntity toEntity(Price domain);
}
