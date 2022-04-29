package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRespository extends JpaRepository<Service, Long> {
}
