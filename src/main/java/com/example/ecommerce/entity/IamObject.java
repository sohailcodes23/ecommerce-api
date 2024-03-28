package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "iam_object_seq", sequenceName = "iam_object_seq", allocationSize = 1)
public class IamObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iam_object_seq")
    private Long id;
    private String username;
    private String password;
    private String status;
    private String role;


    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    private Admin admin;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "delivery_partner_id")
    private DeliveryPartner deliveryPartner;
}
