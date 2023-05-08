package com.learn.deliveryapplication.repo;

import com.learn.deliveryapplication.entity.Order;
import com.learn.deliveryapplication.entity.OrderStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface OrderRepo extends CrudRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o Where o.orderStatus = :status ORDER BY o.createDate ASC LIMIT 1")
    Order getLastOrderByStatus(OrderStatus status);

    @Modifying
    @Query(value = "update Order o SET o.orderStatus = :status, o.finishOrderTime = :finishTime WHERE o.id = :orderId ")
    void finishOrder(OrderStatus status, LocalDateTime finishTime, Long orderId);
}
