package com.ShoppingPlace.ECommerce.Entity;

import com.ShoppingPlace.ECommerce.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="card") // if you not write it will automatically created with name seller in database
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String cardNo;
    private int cvv;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @ManyToOne
    @JoinColumn
    Customer customer;
}
