package com.ecommerce.productprices.infrastructure.persistence.repository;

import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE :startDate BETWEEN p.startDate AND p.endDate " +
            "AND p.productId = :productId " +
            "AND p.brand.id = :brandId " +
            "ORDER BY p.priority DESC, p.priceList " +
            "LIMIT 1")
    Optional<PriceEntity> findTopPrice(LocalDateTime startDate, Long productId, Long brandId);


}
