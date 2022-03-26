package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.OfficeStructure;
import com.example.insuranceprototype.Entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.function.LongFunction;

public interface OfficeStructureRepository extends JpaRepository<OfficeStructure , Long> {

    @Query(value = "select * from office_structure  where up_level_office_id = :id", nativeQuery = true)
    List<OfficeStructure> getUpLevel(Long id);

    @Query(value = "select * from office_structure  where office_level_id = :id", nativeQuery = true)
    List<OfficeStructure> getByOfficeLevel(Long id);

}
