package com.ShoppingPlace.ECommerce.RequestDto;

import com.ShoppingPlace.ECommerce.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDto {
    private String cardNo;
    private int cvv;
    private CardType cardType;
    private int customerId;

}
