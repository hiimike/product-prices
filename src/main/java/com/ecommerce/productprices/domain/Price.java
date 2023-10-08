package com.ecommerce.productprices.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class Price {

    private String brandName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private Long productId;
    private int priority;
    private BigDecimal value;
    private String curr;
}

