package com.learn.deliveryapplication.controller;

import com.learn.deliveryapplication.service.CourierService;
import com.learn.deliveryapplication.service.dto.AddCourierRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourierController {
    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping("add-courier")
    private void addCourier(@RequestBody AddCourierRequest addCourierRequest) {
        courierService.addCourier(addCourierRequest);
    }
}
