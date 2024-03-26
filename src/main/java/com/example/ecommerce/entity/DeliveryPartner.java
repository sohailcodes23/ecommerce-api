package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(name = "delivery_partner_seq", sequenceName = "delivery_partner_seq", allocationSize = 1)
public class DeliveryPartner {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_partner_seq")
    private Long id;
    private String username;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
