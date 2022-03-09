package com.example.insuranceprototype.Proofs;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProofRepo extends JpaRepository<Proof, Long> {

    @Query(value = "select * from proof where is_valid = 1", nativeQuery = true)
    List<Proof> getValidFlag();

}
