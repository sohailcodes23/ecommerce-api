package com.example.ecommerce.repository;

import com.example.demo.entity.IamObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IamObjectRepository extends JpaRepository<IamObject, Long> {

    Optional<IamObject> findByEmailAndStatus(String email, String status);


}
