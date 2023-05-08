package com.learn.deliveryapplication.service;

import com.learn.deliveryapplication.entity.Courier;
import com.learn.deliveryapplication.repo.CourierRepo;
import com.learn.deliveryapplication.service.dto.AddCourierRequest;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl implements CourierService{
    public final CourierRepo courierRepo;

    public CourierServiceImpl( CourierRepo courierRepo) {
        this.courierRepo = courierRepo;
    }

    @Override
    public void addCourier(AddCourierRequest addCourierRequest) {
        courierRepo.save(mapAddCourierResponseToCourier(addCourierRequest));
    }
    public Courier mapAddCourierResponseToCourier(AddCourierRequest addCourierRequest){
        Courier courier = new Courier();
        courier.setName(addCourierRequest.getName());
        courier.setMail(addCourierRequest.getEmail());
        return courier;
    }
}
