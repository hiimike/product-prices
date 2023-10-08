package com.ecommerce.productprices.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findOne(LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId);

}
