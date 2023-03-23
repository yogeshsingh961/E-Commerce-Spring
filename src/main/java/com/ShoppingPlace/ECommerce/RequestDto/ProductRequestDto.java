package com.ShoppingPlace.ECommerce.RequestDto;

import com.ShoppingPlace.ECommerce.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private String name;
    private int price;
    private ProductCategory productCategory;
    private int quantity;
    private  int sellerId;

}