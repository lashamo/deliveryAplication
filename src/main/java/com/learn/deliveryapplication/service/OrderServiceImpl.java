package com.learn.deliveryapplication.service;

import com.learn.deliveryapplication.entity.Courier;
import com.learn.deliveryapplication.entity.Order;
import com.learn.deliveryapplication.entity.OrderStatus;
import com.learn.deliveryapplication.repo.CourierRepo;
import com.learn.deliveryapplication.repo.OrderRepo;
import com.learn.deliveryapplication.service.dto.CreateOrderRequest;
import com.learn.deliveryapplication.service.dto.FinishOrderResponse;
import com.learn.deliveryapplication.service.dto.GetOrderResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final CourierRepo courierRepo;
    private final OrderRepo orderRepo;

    @Value("${com.lear.deliveryapplication.order-pay-coefficient}")
    private double orderPayCoefficient;

    public OrderServiceImpl(CourierRepo courierRepo, OrderRepo orderRepo) {
        this.courierRepo = courierRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void addOder(CreateOrderRequest createOrderRequest) {
        orderRepo.save(mapOrderToCreateOrderRequest(createOrderRequest));
    }

    @Override
    public GetOrderResponse assignOrder(Long courierId) {
        Optional<Courier> optionalCourier = courierRepo.findById(courierId);
        if (optionalCourier.isPresent()) {
            Order order = orderRepo.getLastOrderByStatus(OrderStatus.OPEN);
            order.setOrderStatus(OrderStatus.PROCESSING);
            order.setAssignOrderTime(LocalDateTime.now());
            order.setCourier(optionalCourier.get());
            orderRepo.save(order);
            return mapOrderToGetOrderResponse(order);
        } else {
            throw new RuntimeException("Couldn't courier ID");
        }
    }

    @Override
    @Transactional
    public FinishOrderResponse finishOrder(Long orderId) {
        LocalDateTime now = LocalDateTime.now();
        orderRepo.finishOrder(OrderStatus.FINISHED, now, orderId);
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isPresent()){
            Duration duration = Duration.between(order.get().getAssignOrderTime(), order.get().getFinishOrderTime());
            double spentTime = (double) duration.getSeconds() / 60;
            double orderPay = orderPayCoefficient / spentTime;
            double salary = order.get().getCourier().getSalary() + orderPay;
            order.get().getCourier().setSalary(salary);
            courierRepo.save(order.get().getCourier());
            FinishOrderResponse finishOrderResponse = new FinishOrderResponse();
            finishOrderResponse.setSpentTimeInMinutes(spentTime);
            finishOrderResponse.setOrderPay(orderPay);
            finishOrderResponse.setSalary(salary);
            return finishOrderResponse;
        }else {
            throw new RuntimeException("Couldn't order ID");
        }

    }

    public Order mapOrderToCreateOrderRequest(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setSourceAddress(createOrderRequest.getSourceAddress());
        order.setDestinationAddress(createOrderRequest.getDestinationAddress());
        order.setProductId(createOrderRequest.getProductId());
        order.setReceiverInfo(createOrderRequest.getReceiverInfo());
        order.setCreateDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.OPEN);
        return order;
    }

    public GetOrderResponse mapOrderToGetOrderResponse(Order order) {
        GetOrderResponse getOrderResponse = new GetOrderResponse();
        getOrderResponse.setId(order.getId());
        getOrderResponse.setSourceAddress(order.getSourceAddress());
        getOrderResponse.setDestinationAddress(order.getDestinationAddress());
        getOrderResponse.setProductId(order.getProductId());
        getOrderResponse.setReceiverInfo(order.getReceiverInfo());
        return getOrderResponse;
    }
}
