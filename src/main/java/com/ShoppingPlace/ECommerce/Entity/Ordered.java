package com.ShoppingPlace.ECommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date orderDate;
    private int totalCost;
    private int deliveryCharge;
    private String cardUsedForPayment;
    @ManyToOne
    @JoinColumn
    Customer customer;
    @OneToMany(mappedBy = "ordered",cascade = CascadeType.ALL)
    List<Item> items= new ArrayList<>();
}
