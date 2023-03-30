package com.ShoppingPlace.ECommerce.converter;

import com.ShoppingPlace.ECommerce.Entity.Product;
import com.ShoppingPlace.ECommerce.Entity.Seller;
import com.ShoppingPlace.ECommerce.RequestDto.SellerRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.ProductDto;
import com.ShoppingPlace.ECommerce.ResponseDto.SellerResponseDto;

import java.util.ArrayList;
import java.util.List;

public class SellerConverter {
    public  static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
                return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .mobNo(sellerRequestDto.getMobNo())
                .build();

    }
    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        List<Product> products=seller.getProducts();
        List<ProductDto> productDtoList= new ArrayList<>();
        for(Product p:products){
            ProductDto productDto= new ProductDto();
            productDto.setName(p.getName());
            productDto.setPrice(p.getPrice());
            productDto.setCategory(p.getProductCategory());
            productDtoList.add(productDto);
        }
        return SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .productDtos(productDtoList)
                .build();

    }
}
