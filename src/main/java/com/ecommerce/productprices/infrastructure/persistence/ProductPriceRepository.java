package com.ecommerce.productprices.infrastructure.persistence;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.domain.PriceRepository;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.productprices.infrastructure.persistence.mapper.price.PriceMapper;
import com.ecommerce.productprices.infrastructure.persistence.repository.PriceJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPriceRepository implements PriceRepository {

    private final PriceJpaRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    @Transactional
    public Optional<Price> findTopPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        Optional<PriceEntity> priceEntityOpt = priceRepository.findTopPrice(
                applicationDate, productId, brandId
        );

        return priceEntityOpt.map(priceMapper::toDomain);
    }
}
