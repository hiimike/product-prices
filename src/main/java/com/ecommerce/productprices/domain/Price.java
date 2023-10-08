package com.ecommerce.productprices.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Builder
public record Price(
        Brand brand,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int priceList,
        Long productId,
        int priority,
        BigDecimal value,
        String curr) {

}

