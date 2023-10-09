package com.ecommerce.productprices.application.port.out;

import com.ecommerce.productprices.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetApplicablePrice {

    Optional<Price> handle(LocalDateTime applicationDate, String productId, String brandId);
}
