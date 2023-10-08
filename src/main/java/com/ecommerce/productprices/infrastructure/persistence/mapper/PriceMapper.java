package com.ecommerce.productprices.infrastructure.persistence.mapper;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public Price toDomain(PriceEntity priceEntity) {
        return Price.builder()
                .brandName(priceEntity.getBrand().getName())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priceList(priceEntity.getPriceList())
                .productId(priceEntity.getProductId())
                .priority(priceEntity.getPriority())
                .value(priceEntity.getValue())
                .curr(priceEntity.getCurrency())
                .build();
    }

}
