package com.ecommerce.productprices.infrastructure.api;


import com.ecommerce.productprices.application.port.out.GetApplicablePrice;
import com.ecommerce.productprices.domain.Price;
import com.ecommerce.productprices.infrastructure.api.internal.V1API;
import com.ecommerce.productprices.infrastructure.api.response.PriceResponseV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(value = V1API.BASE_URL)
@RestController
public class GetTopPriceByProductController {

    private final GetApplicablePrice getApplicablePrice;

    @GetMapping("/price/{product_id}")
    @Operation(summary = "Retrieve a price based on date, product ID, and brand ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price found"),
            @ApiResponse(responseCode = "404", description = "Price not found")
    })
    public ResponseEntity<PriceResponseV1> getPrice(
            @RequestParam("date")
            @Parameter(description = "Application date in the format 'yyyy-MM-ddTHH:mm:ss'")
            LocalDateTime applicationDate,

            @PathVariable("product_id")
            @Parameter(description = "Product ID")
            String productId,

            @RequestParam("brand_id")
            @Parameter(description = "Brand ID")
            String brandId) {

        Optional<Price> priceOpt = getApplicablePrice.handle(applicationDate, productId, brandId);

        return priceOpt.map(this::toPriceResponse)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<PriceResponseV1> toPriceResponse(Price price) {
        return ResponseEntity.ok().body(PriceResponseV1.fromDomain(price));
    }
}
