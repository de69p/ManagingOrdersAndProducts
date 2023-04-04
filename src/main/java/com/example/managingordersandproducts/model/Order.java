package com.example.managingordersandproducts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customerName;

    @NotNull
    private String customerEmail;

    @NotNull
    private String customerAddress;

}
