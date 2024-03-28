package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    private Long id;
    private String username;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private List<IamObject> iamObjects;

}
