package com.ShoppingPlace.ECommerce.Service;

import com.ShoppingPlace.ECommerce.Entity.*;
import com.ShoppingPlace.ECommerce.Enum.ProductStatus;
import com.ShoppingPlace.ECommerce.Exception.CustomerNotFoundException;
import com.ShoppingPlace.ECommerce.Exception.ProductNotFoundException;
import com.ShoppingPlace.ECommerce.Repository.CustomerRepository;
import com.ShoppingPlace.ECommerce.Repository.ProductRepository;
import com.ShoppingPlace.ECommerce.RequestDto.OrderRequestDto;

import com.ShoppingPlace.ECommerce.ResponseDto.OrderResponseDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemService itemService;
//    @Autowired
//    JavaMailSender emailSender;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);
        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrdered(order);
        order.getItems().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        // send an email
//        String text = "Congrats your order with total value "+order.getTotalCost()+" has been placed";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("rajputyuvan@gmail.com");
//        message.setTo(customer.getEmail());
//        message.setSubject("Order Placed Notification");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDto;
    }
}