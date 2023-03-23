package com.ShoppingPlace.ECommerce.ResponseDto;

import com.ShoppingPlace.ECommerce.Enum.ProductCategory;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
      private String name;
      private int price;
      private ProductCategory category;
}
