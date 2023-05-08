package com.learn.deliveryapplication.repo;

import com.learn.deliveryapplication.entity.Courier;
import org.springframework.data.repository.CrudRepository;

public interface CourierRepo extends CrudRepository<Courier, Long> {

}
