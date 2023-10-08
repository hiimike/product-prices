package com.ecommerce.productprices.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findTopPrice(LocalDateTime startDate, Long productId, Long brandId);

}
