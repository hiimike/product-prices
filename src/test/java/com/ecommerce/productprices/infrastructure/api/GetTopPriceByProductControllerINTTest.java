package com.ecommerce.productprices.infrastructure.api;

import com.ecommerce.productprices.ProductPricesApplication;
import com.ecommerce.productprices.infrastructure.api.internal.V1API;
import com.ecommerce.productprices.infrastructure.api.response.PriceResponseV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({MockitoExtension.class})
@Import(ProductPricesApplication.class)
class GetTopPriceByProductControllerINTTest {
    private static final String ENDPOINT_URL = V1API.BASE_URL + "/price/{product_id}";
    private LocalDateTime dateParam;
    private String brandIdParam;
    private String productIdParam;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        productIdParam = "35455";
        brandIdParam = "1";
        dateParam = LocalDateTime.now();
    }


    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 10:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch1() {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        // When
        ResponseEntity<PriceResponseV1> response = restTemplate.getForEntity(
                ENDPOINT_URL + "?date={date}&brand_id={brandId}",
                PriceResponseV1.class,
                productIdParam, dateParam.toString(), brandIdParam);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertNotNull(response.getBody());
        assertEquals(expectedStartDate, response.getBody().startDate());
        assertEquals(expectedEndDate, response.getBody().endDate());
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 16:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch2() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 6, 14, 18, 30, 0);

        // When
        ResponseEntity<PriceResponseV1> response = restTemplate.getForEntity(
                ENDPOINT_URL + "?date={date}&brand_id={brandId}",
                PriceResponseV1.class,
                productIdParam, dateParam.toString(), brandIdParam);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertNotNull(response.getBody());
        assertEquals(expectedStartDate, response.getBody().startDate());
        assertEquals(expectedEndDate, response.getBody().endDate());
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 21:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch3() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        // When
        ResponseEntity<PriceResponseV1> response = restTemplate.getForEntity(
                ENDPOINT_URL + "?date={date}&brand_id={brandId}",
                PriceResponseV1.class,
                productIdParam, dateParam.toString(), brandIdParam);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertNotNull(response.getBody());
        assertEquals(expectedStartDate, response.getBody().startDate());
        assertEquals(expectedEndDate, response.getBody().endDate());
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 15 10:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch4() {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 6, 15, 11, 0, 0);

        // When
        ResponseEntity<PriceResponseV1> response = restTemplate.getForEntity(
                ENDPOINT_URL + "?date={date}&brand_id={brandId}",
                PriceResponseV1.class,
                productIdParam, dateParam.toString(), brandIdParam);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertNotNull(response.getBody());
        assertEquals(expectedStartDate, response.getBody().startDate());
        assertEquals(expectedEndDate, response.getBody().endDate());
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 16 21:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch5() {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        LocalDateTime expectedStartDate = LocalDateTime.of(2020, 6, 15, 16, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        // When
        ResponseEntity<PriceResponseV1> response = restTemplate.getForEntity(
                ENDPOINT_URL + "?date={date}&brand_id={brandId}",
                PriceResponseV1.class,
                productIdParam, dateParam.toString(), brandIdParam);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertNotNull(response.getBody());
        assertEquals(expectedStartDate, response.getBody().startDate());
        assertEquals(expectedEndDate, response.getBody().endDate());
    }

}
