package com.ShoppingPlace.ECommerce.Controller;

import com.ShoppingPlace.ECommerce.Exception.ProductNotFoundException;
import com.ShoppingPlace.ECommerce.ResponseDto.ItemResponseDto;
import com.ShoppingPlace.ECommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
  @Autowired
  ItemService itemService;
  @GetMapping("/view/{productId}")
    public ResponseEntity viewITem(@PathVariable("productId") int proudctId){
      ItemResponseDto itemResponseDto;
      try{
        itemResponseDto=itemService.viewItem(proudctId);
      }catch (ProductNotFoundException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
      }
      return new ResponseEntity(itemResponseDto,HttpStatus.ACCEPTED);
  }
}
