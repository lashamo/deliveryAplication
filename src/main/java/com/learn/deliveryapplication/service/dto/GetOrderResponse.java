package com.learn.deliveryapplication.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class GetOrderResponse {
    private Long id;
    private String sourceAddress;
    private String destinationAddress;
    private long productId;
    private String receiverInfo;
}
