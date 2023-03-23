package com.ShoppingPlace.ECommerce.converter;

import com.ShoppingPlace.ECommerce.Entity.Customer;
import com.ShoppingPlace.ECommerce.RequestDto.CustomerRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {
    public static Customer customeRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }
}
