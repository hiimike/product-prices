package com.ecommerce.productprices.application.query;

import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.domain.PriceMother;
import com.ecommerce.productprices.domain.PriceRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

class PriceServiceTest {

    private PriceRepository priceRepository;
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceRepository = Mockito.mock(PriceRepository.class);
        priceService = new PriceService(priceRepository);
    }

    @Test
    void testFindPriceHappyPath() {
        // Given
        Price expected = PriceMother.random();
        String productId = expected.productId().toString();
        String brandId = String.valueOf(new Faker().number().randomNumber());
        Mockito.doReturn(Optional.of(expected))
                .when(priceRepository)
                .findTopPrice(Mockito.any(), Mockito.any(), Mockito.any());

        // When
        Optional<Price> actual = priceService.findPrice(LocalDateTime.now(), productId, brandId);

        // Then
        assertThat(actual).isNotEmpty().contains(expected);
    }

    @Test
    void testShouldThrowNumberFormatExceptionWhenAnInvalidLongIdIsProvidedAsProductId() {
        // Given
        String productId = new Faker().leagueOfLegends().champion();
        String brandId = String.valueOf(new Faker().number().randomNumber());

        // When

        // Then
        Assertions.assertThrows(NumberFormatException.class,
                () -> priceService.findPrice(LocalDateTime.now(), productId, brandId));
    }

    @Test
    void testShouldThrowNumberFormatExceptionWhenAnInvalidLongIdIsProvidedAsBrandId() {
        // Given
        String brandId = new Faker().leagueOfLegends().champion();
        String productId = String.valueOf(new Faker().number().randomNumber());

        // When

        // Then
        Assertions.assertThrows(NumberFormatException.class,
                () -> priceService.findPrice(LocalDateTime.now(), productId, brandId));
    }
}
