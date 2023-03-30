package com.ShoppingPlace.ECommerce.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//but logically when seller is added why show his details again haha
// so simply return "Congraulations! seller has been added"
public class SellerResponseDto {
    private String name;
    private String email;
    private List<ProductDto> productDtos;
    //
}
