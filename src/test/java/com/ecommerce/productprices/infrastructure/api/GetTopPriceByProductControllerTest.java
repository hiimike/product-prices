package com.ecommerce.productprices.infrastructure.api;

import com.ecommerce.productprices.application.query.PriceService;
import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.domain.PriceMother;
import com.ecommerce.productprices.infrastructure.api.internal.GlobalExceptionHandler;
import com.ecommerce.productprices.infrastructure.api.internal.V1API;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class GetTopPriceByProductControllerTest {

    private static final String ENDPOINT_URL = V1API.BASE_URL + "/price/{product_id}";
    private static final String DATE_PARAMETER = "date";
    private static final String BRAND_ID_PARAMETER = "brand_id";
    private static final Faker faker = new Faker();
    private LocalDateTime dateParam;
    private String brandIdParam;
    private String productIdParam;

    private PriceService priceService;
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        productIdParam = String.valueOf(faker.number().randomNumber());
        brandIdParam = String.valueOf(faker.number().randomNumber());
        dateParam = LocalDateTime.now();
        priceService = Mockito.mock(PriceService.class);
        GetTopPriceByProductController controller = new GetTopPriceByProductController(priceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller, new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testShouldReturn404WhenRequestHasValidParameters() throws Exception {

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                .param(DATE_PARAMETER, dateParam.toString())
                .param(BRAND_ID_PARAMETER, brandIdParam)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void testShouldReturnAPriceWhenGivenParamsMatch() throws Exception {
        // Given
        Price price = PriceMother.random();
        Mockito.doReturn(Optional.of(price))
                .when(priceService).findPrice(Mockito.any(), Mockito.any(), Mockito.any());

        // When

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER, dateParam.toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Then
    }

    @Test
    void testShouldReturn400WhenGivenParamsDoNotMatch() throws Exception {

        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testShouldReturn400WhenGivenParamsAreInvalid() throws Exception {
        mockMvc.perform(get(ENDPOINT_URL, productIdParam)
                        .param(DATE_PARAMETER,  faker.date().birthday().toString())
                        .param(BRAND_ID_PARAMETER, brandIdParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
