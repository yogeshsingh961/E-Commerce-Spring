package com.ShoppingPlace.ECommerce.Entity;

import com.ShoppingPlace.ECommerce.Enum.ProductCategory;
import com.ShoppingPlace.ECommerce.Enum.ProductStatus;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;
}