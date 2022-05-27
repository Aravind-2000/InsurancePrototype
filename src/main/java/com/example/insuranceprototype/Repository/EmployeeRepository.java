package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employee where employee_id like %:key% " +
            "or employee_name like %:key%  " +
            "or employee_designation like %:key%  " , nativeQuery = true)
    List<Employee> globalSearch(String key);


}
