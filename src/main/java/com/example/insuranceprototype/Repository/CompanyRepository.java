package com.example.insuranceprototype.Repository;

import java.util.List;
import com.example.insuranceprototype.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query(value = "select * from company where is_valid = 1", nativeQuery = true)
    List<Company> getValidFlag();

    @Query(value = "select * from company where companyId = :id and is_valid = 1", nativeQuery = true)
    Company getCompany(Long id);
}
