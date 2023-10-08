package com.ecommerce.productprices.infrastructure.persistence;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.domain.PriceRepository;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.productprices.infrastructure.persistence.mapper.PriceMapper;
import com.ecommerce.productprices.infrastructure.persistence.repository.PriceJpaRepository;
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
    public Optional<Price> findOne(LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId) {
        Optional<PriceEntity> priceEntityOpt = priceRepository.findPriorityPrice(
                startDate, endDate, productId, brandId
        );

        return priceEntityOpt.map(priceMapper::toDomain);
    }
}
