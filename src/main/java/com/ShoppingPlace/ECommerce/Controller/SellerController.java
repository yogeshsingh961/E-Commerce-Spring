package com.ShoppingPlace.ECommerce.Controller;

import com.ShoppingPlace.ECommerce.RequestDto.SellerRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.SellerResponseDto;
import com.ShoppingPlace.ECommerce.Entity.Seller;
import com.ShoppingPlace.ECommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
      return sellerService.addSeller(sellerRequestDto);
    }



    @GetMapping("/view-seller")
   public ResponseEntity viewSeller(@RequestParam int id) throws Exception{
       SellerResponseDto sellerResponseDto;
       try{
           sellerResponseDto=sellerService.viewSeller(id);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
       }


        return new ResponseEntity(sellerResponseDto,HttpStatus.ACCEPTED);

    }
    @GetMapping("/find-sellers")
    public ResponseEntity findALlSellers() {
        List<SellerResponseDto> sellerResponseDtos;
        try {
            sellerResponseDtos=sellerService.findAllSellers();
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(sellerResponseDtos, HttpStatus.ACCEPTED);
    }
    //get a seller by pan card
    //find sellers of a particular age
    //delete seller by id
    // delete all sellers

}
