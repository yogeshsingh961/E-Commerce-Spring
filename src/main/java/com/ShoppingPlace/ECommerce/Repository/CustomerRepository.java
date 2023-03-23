package com.ShoppingPlace.ECommerce.Repository;

import com.ShoppingPlace.ECommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
