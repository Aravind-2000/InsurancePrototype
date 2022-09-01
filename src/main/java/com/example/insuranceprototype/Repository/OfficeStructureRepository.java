package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.OfficeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficeStructureRepository extends JpaRepository<OfficeStructure , Long> {

    @Query(value = "select * from office_structure where office_status = 'active' ", nativeQuery = true)
    List<OfficeStructure> getAllActiveOffices();

    @Query(value = "select * from office_structure  where up_level_office_id = :id", nativeQuery = true)
    List<OfficeStructure> getUpLevel(Long id);

    @Query(value = "select * from office_structure  where office_level_id = :id", nativeQuery = true)
    List<OfficeStructure> getByOfficeLevel(Long id);

    @Query(value = "select * from office_structure where office_id like %:key% and office_status = 'active' " +
            "or office_name like %:key% and office_status = 'active' " , nativeQuery = true)
    List<OfficeStructure> globalSearch(String key);

}
