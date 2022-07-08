package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.ProductCoverageNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCoverageRepository extends JpaRepository<ProductCoverageNames, Long> {



    @Query(value = "select * from product_coverage_names where valid_flag = 1", nativeQuery = true)
    List<ProductCoverageNames> getAllValids();

}
