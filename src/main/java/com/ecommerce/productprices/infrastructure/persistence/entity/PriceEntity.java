package com.ecommerce.productprices.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_seq")
    @SequenceGenerator(name = "price_seq", sequenceName = "PRICE_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private BrandEntity brand;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURRENCY")
    private String currency;

}

