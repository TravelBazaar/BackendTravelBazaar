package com.upc.edu.Backend_TravelBazaar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_created", length = 50)
    private LocalDateTime cartDateCreated;

    @Column(name = "status", length = 50)
    private String cartStatus;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
