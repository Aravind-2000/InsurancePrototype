package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.ServiceMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceMethodRepository extends JpaRepository<ServiceMethods, Long> {

    @Query(value = "select * from service_methods where valid_flag = 1", nativeQuery = true)
    List<ServiceMethods> getAllValids();


    @Query(value = "select method_name from service_methods where valid_flag = 1 and service_id = :serviceId", nativeQuery = true)
    List<String> getByServiceId(Long serviceId);

}
