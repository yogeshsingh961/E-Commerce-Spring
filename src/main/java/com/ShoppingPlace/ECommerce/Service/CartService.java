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

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderService orderService;
//    @Autowired
//    JavaMailSender emailSender;
    public String addToCart(OrderRequestDto orderRequestDto) throws Exception {

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
        if(product.getProductStatus()== ProductStatus.OUT_OF_STOCK)
            throw new Exception("Kindly write your email id ,AVAILABLE SOON!");

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        Cart cart = customer.getCart();

        int newCost = cart.getCardTotal() + orderRequestDto.getRequiredQuantity()*product.getPrice();
        cart.setCardTotal(newCost);

        // Add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getLists().add(item);

        customerRepository.save(customer);

        return "Item has been added to your Cart!!";
    }

    public List<OrderResponseDto> checkOut(int customerId) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }
        List<OrderResponseDto> orderResponseDtos=new ArrayList<>();
        int totalCost = customer.getCart().getCardTotal();
        Cart cart=customer.getCart();
        for(Item item:cart.getLists()){
            Ordered order= new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);


            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            //prepare response dto
            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();
            orderResponseDtos.add(orderResponseDto);

        }
        cart.setLists(new ArrayList<>());
        cart.setCardTotal(0);
        customerRepository.save(customer);

//        String text = "Congrats your order with total value "+totalCost+" has been placed";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("rajputyuvan@gmail.com");
//        message.setTo(customer.getEmail());
//        message.setSubject("Order Placed from E-Commerce website");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDtos;

    }
}

