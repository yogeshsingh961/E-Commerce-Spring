package com.ShoppingPlace.ECommerce.Service;

import com.ShoppingPlace.ECommerce.Entity.Cart;
import com.ShoppingPlace.ECommerce.Entity.Customer;
import com.ShoppingPlace.ECommerce.Repository.CustomerRepository;
import com.ShoppingPlace.ECommerce.RequestDto.CustomerRequestDto;
import com.ShoppingPlace.ECommerce.converter.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public String addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer= CustomerConverter.customeRequestDtoToCustomer(customerRequestDto);
        //allocate the cart to customer
        Cart cart = new Cart();
        cart.setCardTotal(0);
        cart.setCustomer(customer);

        //set the cart to customer
        customer.setCart(cart);
        customerRepository.save(customer);
        return "Congratulations!"+customer.getName()+" your account has been created";

    }
}
