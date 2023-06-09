package com.ShoppingPlace.ECommerce.Service;

import com.ShoppingPlace.ECommerce.Entity.Product;
import com.ShoppingPlace.ECommerce.Exception.SellerNotFoundException;
import com.ShoppingPlace.ECommerce.RequestDto.SellerRequestDto;
import com.ShoppingPlace.ECommerce.ResponseDto.ProductDto;
import com.ShoppingPlace.ECommerce.ResponseDto.SellerResponseDto;
import com.ShoppingPlace.ECommerce.Entity.Seller;
import com.ShoppingPlace.ECommerce.Repository.SellerRepository;
import com.ShoppingPlace.ECommerce.converter.SellerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public String addSeller(SellerRequestDto sellerRequestDto ){
        //old way using creating object using new keyword
//        Seller seller= new Seller();
//        seller.setEmail(sellerRequestDto.getEmail());
//        seller.setName(sellerRequestDto.getName());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setPanNo(sellerRequestDto.getPanNo());
//        sellerRepository.save(seller);
        // using Builder creating objects and setting attributes of seller class

       // in 1 line attributes set using Builder
        // Seller seller= Seller.builder().name(sellerRequestDto.getName()).email(sellerRequestDto.getEmail()).mobNo(sellerRequestDto.getMobNo()).panNo(sellerRequestDto.getPanNo()).build();
//        Seller seller= Seller.builder()
//                .name(sellerRequestDto.getName())
//                .email(sellerRequestDto.getEmail())
//                .panNo(sellerRequestDto.getPanNo())
//                .mobNo(sellerRequestDto.getMobNo())
//                .build();
//        sellerRepository.save(seller);

        // to clean the code on line 24 we use converter(DtoToEntity here it is DtoToSeller)
        Seller seller = SellerConverter.SellerRequestDtoToSeller(sellerRequestDto);
        sellerRepository.save(seller);
         return "Congrats!"+seller.getName()+" Welcome to E-Commerce website!!";

    }

   public SellerResponseDto viewSeller(int id) throws Exception {
        Seller seller;
        try {
            seller = sellerRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Seller not found");
        }
        SellerResponseDto sellerResponseDto= new SellerResponseDto();
        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setEmail(seller.getEmail());

       List<Product> products=seller.getProducts();
       List<ProductDto> productDtoList= new ArrayList<>();
       for(Product p:products){
           ProductDto productDto= new ProductDto();
           productDto.setName(p.getName());
           productDto.setPrice(p.getPrice());
           productDto.setCategory(p.getProductCategory());
           productDtoList.add(productDto);
       }
       sellerResponseDto.setProductDtos(productDtoList);
        return sellerResponseDto;

        }
        public List<SellerResponseDto> findAllSellers() throws SellerNotFoundException {
            List<Seller> sellers;
        try {
          sellers = sellerRepository.findAll();
        }catch (Exception e){
            throw new SellerNotFoundException("No seller added");
        }

        List<SellerResponseDto> sellerResponseDto= new ArrayList<>();
         for(Seller s:  sellers){
             SellerResponseDto sellerResponseDto1=SellerConverter.SellerToSellerResponseDto(s);
             sellerResponseDto.add(sellerResponseDto1);
         }

         return sellerResponseDto;

        }
}
