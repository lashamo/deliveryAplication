package com.learn.deliveryapplication.service;

import com.learn.deliveryapplication.service.dto.CreateOrderRequest;
import com.learn.deliveryapplication.service.dto.FinishOrderResponse;
import com.learn.deliveryapplication.service.dto.GetOrderResponse;

public interface OrderService {
    void addOder(CreateOrderRequest createOrderRequest);

    GetOrderResponse assignOrder(Long courierId);

    FinishOrderResponse finishOrder(Long orderId);
}
