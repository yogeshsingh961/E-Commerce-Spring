package com.ShoppingPlace.ECommerce.Service;

import com.ShoppingPlace.ECommerce.Entity.Item;
import com.ShoppingPlace.ECommerce.Entity.Product;
import com.ShoppingPlace.ECommerce.Exception.ProductNotFoundException;
import com.ShoppingPlace.ECommerce.Repository.ProductRepository;
import com.ShoppingPlace.ECommerce.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
        @Autowired
        ProductRepository productRepository;
        public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {
         Product product;
         try{
             product=productRepository.findById(productId).get();
        }catch (Exception e){
             throw new ProductNotFoundException("Sorry! Invalid product id");
         }
         Item item= Item.builder()
                 .requiredQuantity(0)
                 .product(product)
                 .build();

         //map proudct to item
         product.setItem(item);
         productRepository.save(product);//save both item & product

          // prepare the ItemResponse dto
          ItemResponseDto itemResponseDto= ItemResponseDto.builder()
                  .productName(product.getName())
                  .price(product.getPrice())
                  .productCategory(product.getProductCategory())
                  .productStatus(product.getProductStatus())
                  .build();
          return itemResponseDto;
    }

}
