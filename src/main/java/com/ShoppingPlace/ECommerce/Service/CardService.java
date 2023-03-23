package com.ShoppingPlace.ECommerce.Service;

import com.ShoppingPlace.ECommerce.Controller.CardController;
import com.ShoppingPlace.ECommerce.Entity.Card;
import com.ShoppingPlace.ECommerce.Entity.Customer;
import com.ShoppingPlace.ECommerce.Exception.CustomerNotFoundException;
import com.ShoppingPlace.ECommerce.Repository.CustomerRepository;
import com.ShoppingPlace.ECommerce.RequestDto.CardRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.CardDto;
import com.ShoppingPlace.ECommerce.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
      Customer customer;
      try{
          customer=customerRepository.findById(cardRequestDto.getCustomerId()).get();
      }catch (Exception e){
          throw new CustomerNotFoundException("Invalid customer id");
      }
      //make card object
        Card card= Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer) //either this or line 30 both are same
                .build();
        //card.setCustomer(customer);

        //add the card to current card list of customer
        customer.getCards().add(card);
        customerRepository.save(customer); //save both customer & card

        //prepare CardResponseDto
        CardResponseDto cardResponseDto= new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        //convert every card to cardDtos
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1:customer.getCards()){
            CardDto cardDto=new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }
        cardResponseDto.setCardDtos(cardDtoList);
        return cardResponseDto;

    }
}
