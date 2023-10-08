package com.ecommerce.productprices.infrastructure.persistence;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.domain.PriceMother;
import com.ecommerce.productprices.domain.PriceRepository;
import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.productprices.infrastructure.persistence.mapper.brand.BrandMapperH2;
import com.ecommerce.productprices.infrastructure.persistence.mapper.price.PriceMapper;
import com.ecommerce.productprices.infrastructure.persistence.mapper.price.PriceMapperH2;
import com.ecommerce.productprices.infrastructure.persistence.repository.PriceJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ecommerce.productprices.util.TestUtil.futureLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@EnableJpaRepositories(basePackages = "com.ecommerce.productprices.infrastructure.persistence.repository")
@Import({PriceMapperH2.class, BrandMapperH2.class, ProductPriceRepository.class})
class ProductPriceRepositoryINTTest {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private PriceRepository repository;

    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 10:00:00 then the price is retrieved")
    void testGetPriceForApplicationDate1() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, 35455L, 1L);

        // Then
        assertThat(result).isNotEmpty();
        assertEquals(result.get().startDate(), expectedStartDate);
        assertEquals(result.get().endDate(), expectedEndDate);
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 16:00:00 then the price is retrieved")
    void testGetPriceForApplicationDate2() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 6, 14, 18, 30, 0);

        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, 35455L, 1L);

        // Then
        assertThat(result).isNotEmpty();
        assertEquals(result.get().startDate(), expectedStartDate);
        assertEquals(result.get().endDate(), expectedEndDate);
    }
    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 21:00:00 then the price is retrieved")
    void testGetPriceForApplicationDate3() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, 35455L, 1L);

        // Then
        assertThat(result).isNotEmpty();
        assertEquals(result.get().startDate(), expectedStartDate);
        assertEquals(result.get().endDate(), expectedEndDate);
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 15 10:00:00 then the price is retrieved")
    void testGetPriceForApplicationDate4() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 6, 15, 11, 0, 0);

        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, 35455L, 1L);

        // Then
        assertThat(result).isNotEmpty();
        assertEquals(result.get().startDate(), expectedStartDate);
        assertEquals(result.get().endDate(), expectedEndDate);
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 16 21:00:00 then the price is retrieved")
    void testGetPriceForApplicationDate5() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 15, 16, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, 35455L, 1L);

        // Then
        assertThat(result).isNotEmpty();
        assertEquals(result.get().startDate(), expectedStartDate);
        assertEquals(result.get().endDate(), expectedEndDate);
    }

    @Test
    void testSaveAPriceThenRetrieveItWithAllProperties() {
        // Given
        Price expected = PriceMother.random();
        PriceEntity savedPriceEntity = priceJpaRepository.save(priceMapper.toEntity(expected));
        Long brandId = savedPriceEntity.getBrand().getId();

        // When
        Optional<Price> result = repository.findTopPrice(LocalDateTime.now(), expected.productId(), brandId);

        // Then
        assertThat(result).isNotEmpty().contains(expected);
    }

    @Test
    void testSaveAPriceThenGetNullObjectDueToNotPriceOnGivenDate() {
        // Given
        LocalDateTime applicationDate = futureLocalDateTime(60);
        Price expected = PriceMother.random();
        PriceEntity savedPriceEntity = priceJpaRepository.save(priceMapper.toEntity(expected));
        Long brandId = savedPriceEntity.getBrand().getId();

        // When
        Optional<Price> result = repository.findTopPrice(applicationDate, expected.productId(), brandId);

        // Then
        assertThat(result).isEmpty();
    }


}
