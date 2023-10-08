package com.ecommerce.productprices.application.query;

import com.ecommerce.productprices.domain.PriceRepository;
import com.ecommerce.productprices.domain.Price;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public Optional<Price> findPrice(LocalDateTime applicationDate, String productId, String brandId) {
        try {
            return priceRepository.findTopPrice(
                    applicationDate,
                    Long.parseLong(productId),
                    Long.parseLong(brandId)
            );
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input");
        }
    }

}
