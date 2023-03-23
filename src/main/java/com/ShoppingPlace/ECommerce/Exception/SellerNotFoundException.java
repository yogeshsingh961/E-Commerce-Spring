package com.ShoppingPlace.ECommerce.Exception;

import com.ShoppingPlace.ECommerce.Entity.Seller;

public class SellerNotFoundException extends Exception{
    public SellerNotFoundException(String message){
        super(message);
    }
}
