package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.Employee;
import com.example.insuranceprototype.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository empRepo;

    public List<Employee> getallEmployees(){
        return empRepo.findAll();
    }

    public Optional<Employee> getempById(Long id){
        return empRepo.findById(id);
    }

    public String addEmployee(Employee employee){
        empRepo.save(employee);
        return  "Employee" +  employee.getEmployeeName() + " added successfully";
    }

    public String updateEmpDetails(Long id , Employee employee){
        Employee emp = empRepo.getById(id);

        if(employee.getEmployeeId() != null){
            emp.setEmployeeId(employee.getEmployeeId());
        }
        if(employee.getEmployeeName() != null){
            emp.setEmployeeName(employee.getEmployeeName());
        }
        if(employee.getEmployeeDesignation() != null){
            emp.setEmployeeDesignation(employee.getEmployeeDesignation());
        }
        empRepo.save(emp);
        return "EMployee" + employee.getEmployeeName() + "details updated successfully";
    }

}
