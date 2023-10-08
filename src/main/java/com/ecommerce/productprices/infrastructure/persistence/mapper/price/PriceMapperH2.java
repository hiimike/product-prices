package com.ecommerce.productprices.infrastructure.persistence.mapper.price;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.productprices.infrastructure.persistence.mapper.brand.BrandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PriceMapperH2 implements PriceMapper {

    private final BrandMapper brandMapper;

    @Override
    public Price toDomain(PriceEntity entity) {
        return Price.builder()
                .brand(brandMapper.toDomain(entity.getBrand()))
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .value(entity.getPrice())
                .curr(entity.getCurrency())
                .build();
    }

    @Override
    public PriceEntity toEntity(Price domain) {
        return PriceEntity.builder()
                .brand(brandMapper.toEntity(domain.brand()))
                .startDate(domain.startDate())
                .endDate(domain.endDate())
                .priceList(domain.priceList())
                .productId(domain.productId())
                .priority(domain.priority())
                .price(domain.value())
                .currency(domain.curr())
                .build();
    }

}
