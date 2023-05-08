package com.learn.deliveryapplication.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String sourceAddress;
    private String destinationAddress;
    private int productId;
    private String receiverInfo;
}
