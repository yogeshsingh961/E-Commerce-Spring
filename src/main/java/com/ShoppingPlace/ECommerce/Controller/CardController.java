package com.ShoppingPlace.ECommerce.Controller;

import com.ShoppingPlace.ECommerce.Exception.CustomerNotFoundException;
import com.ShoppingPlace.ECommerce.RequestDto.CardRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.CardResponseDto;
import com.ShoppingPlace.ECommerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        CardResponseDto cardResponseDto;
        try {
           cardResponseDto= cardService.addCard(cardRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cardResponseDto, HttpStatus.ACCEPTED);
    }
    //remove card
    //remove all cards for particular customer id;
    //
}
