package com.ShoppingPlace.ECommerce.Service;

import com.ShoppingPlace.ECommerce.Enum.ProductCategory;
import com.ShoppingPlace.ECommerce.Enum.ProductStatus;
import com.ShoppingPlace.ECommerce.Exception.SellerNotFoundException;
import com.ShoppingPlace.ECommerce.RequestDto.ProductRequestDto;

import com.ShoppingPlace.ECommerce.ResponseDto.ProductResponseDto;
import com.ShoppingPlace.ECommerce.Entity.Product;
import com.ShoppingPlace.ECommerce.Entity.Seller;
import com.ShoppingPlace.ECommerce.Repository.ProductRepository;
import com.ShoppingPlace.ECommerce.Repository.SellerRepository;
import com.ShoppingPlace.ECommerce.converter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws Exception {
        Seller seller;
        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (Exception e){
            throw new SellerNotFoundException("seller not found for this product");
        }
//        Product product= new Product();
//        product.setName(productRequestDto.getName());
//        product.setCategory(productRequestDto.getCategory());
//        product.setQuantity(productRequestDto.getQuantity());
//        product.setPrice(productRequestDto.getPrice());
//        product.setSeller(seller);
//        seller.getProducts().add(product);
//        sellerRepository.save(seller);

        //using converter convert this Dto to product(entity)
        Product product= ProductConverter.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);
        sellerRepository.save(seller);
        // set ProductResponseDto
//        ProductResponseDto productResponseDto=new ProductResponseDto();
//        productResponseDto.setProductName(product.getName());
//        productResponseDto.setSellerName(seller.getName());

        //setting using converter(entity(Product)ToDto & preparing response


        ProductResponseDto productResponseDto=ProductConverter.productToProductResponseDto(product);
        productResponseDto.setSellerName(seller.getName());
        return productResponseDto;

    }
    public List<ProductResponseDto> getProductsByProductCategory(ProductCategory productCategory) throws Exception {
        List<Product> products;
        try{
            products=productRepository.findAllByProductCategory(productCategory);
        }catch (Exception e){
            throw new Exception("No such category found");
        }
        List<ProductResponseDto>productResponseDtos= new ArrayList<>();
        for(Product product: products){
            ProductResponseDto productResponseDto=ProductConverter.productToProductResponseDto(product);
            productResponseDto.setSellerName(product.getSeller().getName());
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

}
