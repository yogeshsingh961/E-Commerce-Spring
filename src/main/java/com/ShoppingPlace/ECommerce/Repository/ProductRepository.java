package com.ShoppingPlace.ECommerce.Repository;

import com.ShoppingPlace.ECommerce.Entity.Product;
import com.ShoppingPlace.ECommerce.Enum.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
 List<Product> findAllByProductCategory(ProductCategory productCategory);
}
