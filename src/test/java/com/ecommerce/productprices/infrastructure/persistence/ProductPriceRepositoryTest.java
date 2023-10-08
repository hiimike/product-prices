package com.ecommerce.productprices.infrastructure.persistence;

import com.ecommerce.productprices.domain.Brand;
import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntityMother;
import com.ecommerce.productprices.infrastructure.persistence.mapper.price.PriceMapper;
import com.ecommerce.productprices.infrastructure.persistence.repository.PriceJpaRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ecommerce.productprices.util.TestUtil.futureLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class ProductPriceRepositoryTest {

    private PriceJpaRepository priceJpaRepository;
    private PriceMapper priceMapper;
    private ProductPriceRepository repository;

    @BeforeEach
    void setUp() {
        priceJpaRepository = Mockito.mock(PriceJpaRepository.class);
        priceMapper = Mockito.mock(PriceMapper.class);
        repository = new ProductPriceRepository(priceJpaRepository, priceMapper);
    }

    @Test
    void testSaveAPriceThenRetrieveItWithAllProperties() {
        // Given
        Long brandId = new Faker().random().nextLong();
        PriceEntity entity = PriceEntityMother.random();
        Long productId = entity.getProductId();
        Mockito.doReturn(Optional.of(entity))
                .when(priceJpaRepository)
                .findTopPrice(Mockito.any(), Mockito.any(), Mockito.eq(brandId));

        Price expected = getExpectedPrice(entity);
        Mockito.doReturn(expected).when(priceMapper).toDomain(entity);


        // When
        Optional<Price> result = repository.findTopPrice(LocalDateTime.now(), productId, brandId);

        // Then
        assertThat(result).isNotEmpty().contains(expected);
    }

    private Price getExpectedPrice(PriceEntity entity) {
        return Price.builder()
                .brand(Brand.builder()
                        .name(entity.getBrand().getName())
                        .build())
                .productId(entity.getProductId())
                .priceList(entity.getPriceList())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .value(entity.getPrice())
                .curr(entity.getCurrency())
                .priority(entity.getPriority())
                .build();
    }

    @Test
    void testGetNullObjectDueToNotPriceOnGivenDate() {
        // Given
        LocalDateTime applicationDate = futureLocalDateTime(30);
        Long productId = new Faker().random().nextLong();
        Long brandId = new Faker().random().nextLong();

        Mockito.doReturn(Optional.empty())
                .when(priceJpaRepository)
                .findTopPrice(Mockito.any(), Mockito.any(), Mockito.any());


        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, productId, brandId);

        // Then
        assertThat(result).isEmpty();

    }
}
