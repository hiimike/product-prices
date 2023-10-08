package com.ecommerce.productprices.infrastructure.api;

import com.ecommerce.productprices.ProductPricesApplication;
import com.ecommerce.productprices.application.query.PriceService;
import com.ecommerce.productprices.infrastructure.api.internal.GlobalExceptionHandler;
import com.ecommerce.productprices.infrastructure.api.internal.V1API;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({MockitoExtension.class})
@Import(ProductPricesApplication.class)
@AutoConfigureMockMvc
class GetTopPriceByProductControllerINTTest {
    private static final String ENDPOINT_URL = V1API.BASE_URL + "/price/{product_id}";
    private static final String DATE_PARAMETER = "date";
    private static final String BRAND_ID_PARAMETER = "brand_id";
    private static final Faker faker = new Faker();
    private LocalDateTime dateParam;
    private String brandIdParam;
    private String productIdParam;

    @Autowired
    private PriceService priceService;
    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        productIdParam = "35455";
        brandIdParam = "1";
        dateParam = LocalDateTime.now();
        GetTopPriceByProductController controller = new GetTopPriceByProductController(priceService);
        //jackson2HttpMessageConverter.setObjectMapper(new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE));
        mockMvc = MockMvcBuilders.standaloneSetup(
                controller, new GlobalExceptionHandler())
                .setMessageConverters(jackson2HttpMessageConverter)
                .build();
    }


    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 10:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch1() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 14, 10,0 ,0);

        // When

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER, dateParam.toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.start_date").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.end_date").value("2020-12-31T23:59:59"));

        // Then
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 16:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch2() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 14, 16,0 ,0);

        // When

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER, dateParam.toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.start_date").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.end_date").value("2020-06-14T18:30:00"));

        // Then
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 14 21:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch3() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 14, 21,0 ,0);

        // When

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER, dateParam.toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.start_date").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.end_date").value("2020-12-31T23:59:59"));

        // Then
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 15 10:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch4() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 15, 10,0 ,0);

        // When

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER, dateParam.toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.start_date").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.end_date").value("2020-06-15T11:00:00"));

        // Then
    }

    @Test
    @DisplayName("Given a price when the application date is 2020 06 16 21:00:00 then the price is retrieved")
    void testShouldReturnAPriceWhenGivenParamsMatch5() throws Exception {
        // Given
        dateParam = LocalDateTime.of(2020, 6, 16, 21,0 ,0);

        // When

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER, dateParam.toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.start_date").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.end_date").value("2020-12-31T23:59:59"));

        // Then
    }
}
