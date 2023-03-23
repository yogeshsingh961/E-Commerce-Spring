package com.ShoppingPlace.ECommerce.Controller;


import com.ShoppingPlace.ECommerce.Enum.ProductCategory;
import com.ShoppingPlace.ECommerce.RequestDto.ProductRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.ProductResponseDto;
import com.ShoppingPlace.ECommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws Exception {
        ProductResponseDto productResponseDto;
        try {
            productResponseDto = productService.addProduct(productRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(productResponseDto, HttpStatus.ACCEPTED);

    }
    @GetMapping("/get-category/{productCategory}")
    public ResponseEntity getProductsByProductCategory(@PathVariable ProductCategory productCategory){
        List<ProductResponseDto>productResponseDto;
        try{
         productResponseDto=    productService.getProductsByProductCategory(productCategory);
        }catch (Exception e){
           return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
    }
}
