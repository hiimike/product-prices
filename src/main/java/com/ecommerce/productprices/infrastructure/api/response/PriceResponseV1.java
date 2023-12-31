package com.ecommerce.productprices.infrastructure.api.response;

import com.ecommerce.productprices.domain.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponseV1(
        String brandName,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency,
        Long productId
) {
    public static PriceResponseV1 fromDomain(Price price) {
        return new PriceResponseV1(
                price.brand().name(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.value(),
                price.curr(),
                price.productId()
        );
    }

}
