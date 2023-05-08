package com.learn.deliveryapplication.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FinishOrderResponse {
    private double spentTimeInMinutes;
    private double orderPay;
    private double salary;
}
