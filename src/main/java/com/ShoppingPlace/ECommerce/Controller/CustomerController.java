package com.ShoppingPlace.ECommerce.Controller;

import com.ShoppingPlace.ECommerce.RequestDto.CustomerRequestDto;
import com.ShoppingPlace.ECommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
       return customerService.addCustomer(customerRequestDto);
    }

    //get customer by id
    //view all customer
    // delete customer by id
    //get customer by email
    //update customer

}
