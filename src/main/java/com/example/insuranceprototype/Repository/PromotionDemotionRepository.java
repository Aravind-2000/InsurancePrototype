package com.example.insuranceprototype.Repository;


import com.example.insuranceprototype.Entity.Promotion_Demotion_Details;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PromotionDemotionRepository extends JpaRepository<Promotion_Demotion_Details, Long> {

}
