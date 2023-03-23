package com.ShoppingPlace.ECommerce.converter;

import com.ShoppingPlace.ECommerce.Entity.Seller;
import com.ShoppingPlace.ECommerce.RequestDto.SellerRequestDto;

public class SellerConverter {
    public  static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
                return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .mobNo(sellerRequestDto.getMobNo())
                .build();


    }
}
