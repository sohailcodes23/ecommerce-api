package com.example.ecommerce.repository;

import com.example.ecommerce.entity.IamObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IamObjectRepository extends JpaRepository<IamObject, Long> {

    Optional<IamObject> findByUsernameAndStatus(String email, String status);


}
