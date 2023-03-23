package com.ShoppingPlace.ECommerce.ResponseDto;

import com.ShoppingPlace.ECommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private String productName;
    private String sellerName;
    private int price;
    private ProductStatus productStatus;
    private int quantity;


}
