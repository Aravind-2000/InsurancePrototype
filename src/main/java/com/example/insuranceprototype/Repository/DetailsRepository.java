package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetailsRepository extends JpaRepository<PersonalDetails, Long> {
}
