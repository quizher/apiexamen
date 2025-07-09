package com.enrique.apiexamen.repository;

import com.enrique.apiexamen.entity.Order;
import com.enrique.apiexamen.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("""
        SELECT o FROM Order o
        WHERE (:status IS NULL OR o.status = :status)
        AND (:origin IS NULL OR o.origin LIKE %:origin%)
        AND (:destination IS NULL OR o.destination LIKE %:destination%)
        AND (:fromDate IS NULL OR o.createdAt >= :fromDate)
        AND (:toDate IS NULL OR o.createdAt <= :toDate)
    """)
    List<Order> findWithFilters(
            @Param("status") OrderStatus status,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            @Param("origin") String origin,
            @Param("destination") String destination
    );
}
