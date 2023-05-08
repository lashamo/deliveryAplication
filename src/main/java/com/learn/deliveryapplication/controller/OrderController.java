package com.learn.deliveryapplication.controller;

import com.learn.deliveryapplication.service.OrderService;
import com.learn.deliveryapplication.service.dto.CreateOrderRequest;
import com.learn.deliveryapplication.service.dto.FinishOrderResponse;
import com.learn.deliveryapplication.service.dto.GetOrderResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("add-order")
    public void addOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.addOder(createOrderRequest);
    }

    @PutMapping("assign-order")
    public GetOrderResponse getOrderByDateAndStatus(@RequestParam Long courierId) {
        return orderService.assignOrder(courierId);
    }

    @PostMapping("finish-order")
    public FinishOrderResponse finishOrder(@RequestParam Long orderId) {
        return orderService.finishOrder(orderId);
    }
}
