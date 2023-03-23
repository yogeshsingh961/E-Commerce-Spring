package com.ShoppingPlace.ECommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    @Column(unique = true)
    private String mobNo;
    @Column(unique = true)
    private String email;
    private int age;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cards= new ArrayList<>();

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orders= new ArrayList<>();
}
