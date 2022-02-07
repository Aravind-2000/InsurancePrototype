package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
