package com.ecommerce.productprices.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE")
@Getter
@Setter
public class PriceEntity extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "CURRENCY")
    private String currency;

}

