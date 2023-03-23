package com.ShoppingPlace.ECommerce.converter;

import com.ShoppingPlace.ECommerce.Entity.Product;
import com.ShoppingPlace.ECommerce.Entity.Seller;
import com.ShoppingPlace.ECommerce.Enum.ProductStatus;
import com.ShoppingPlace.ECommerce.RequestDto.ProductRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.ProductResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass // use not to create object if we use it shows error because utility class should not be created
public class ProductConverter {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
             //  can't set selller here because we don't have seller object we have only seller id
                .build();

    }
    public static ProductResponseDto productToProductResponseDto(Product product){
        ProductResponseDto productResponseDto=ProductResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .quantity(product.getQuantity())
//                .sellerName(seller.getName())
                .build();
        return productResponseDto;
    }

}
