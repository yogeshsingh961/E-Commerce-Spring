package com.ShoppingPlace.ECommerce.ResponseDto;

import com.ShoppingPlace.ECommerce.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    private CardType cardType;
    private String cardNo;

}
