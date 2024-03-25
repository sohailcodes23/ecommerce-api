package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@SequenceGenerator(name = "iam_object_seq", sequenceName = "iam_object_seq", allocationSize = 1)
public class IamObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iam_object_seq")
    private Long id;
    private String email;
    private String password;
    private String status;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "iamObject")
    private List<Customer> customers;
}
