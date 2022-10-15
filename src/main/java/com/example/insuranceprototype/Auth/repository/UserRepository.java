package com.example.insuranceprototype.Auth.repository;


import com.example.insuranceprototype.Auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


  Optional<User> findByEmail(String email);

  @Query(value = "select * from users where email = :email", nativeQuery = true)
  User findUserByEmail(String email);


  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
