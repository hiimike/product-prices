package com.ecommerce.productprices.infrastructure.persistence.repository;

import com.ecommerce.productprices.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "SELECT * FROM price " +
            "WHERE start_date <= :startDate " +
            "AND end_date >= :endDate " +
            "AND product_id = :productId " +
            "AND brand_id = :brandId " +
            "ORDER BY priority " +
            "DESC LIMIT 1",
            nativeQuery = true)
    Optional<PriceEntity> findPriorityPrice(LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId);

}
