package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Employee;
import com.example.insuranceprototype.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @GetMapping("/getall")
    public List<Employee> getallemp(){
        return empService.getallEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getempbyid(@PathVariable Long id){
        return empService.getempById(id);
    }

    @PostMapping("/add")
    public String addemp(@RequestBody Employee employee){
        return empService.addEmployee(employee);
    }

    @PatchMapping("/{id}")
    public String updateempdetails(@PathVariable Long id, @RequestBody Employee employee){
        return empService.updateEmpDetails(id, employee);
    }

}
