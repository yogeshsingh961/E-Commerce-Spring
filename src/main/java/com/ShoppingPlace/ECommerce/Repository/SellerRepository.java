package com.ShoppingPlace.ECommerce.Repository;

import com.ShoppingPlace.ECommerce.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

}
