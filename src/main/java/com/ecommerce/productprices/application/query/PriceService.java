package com.ecommerce.productprices.application.query;

import com.ecommerce.productprices.application.port.out.GetApplicablePrice;
import com.ecommerce.productprices.domain.PriceRepository;
import com.ecommerce.productprices.domain.Price;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements GetApplicablePrice {

    private final PriceRepository priceRepository;

    public Optional<Price> handle(LocalDateTime applicationDate, String productId, String brandId) {
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
