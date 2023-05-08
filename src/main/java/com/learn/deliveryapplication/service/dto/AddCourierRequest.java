package com.learn.deliveryapplication.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCourierRequest {
    private String name;
    private String email;
}
