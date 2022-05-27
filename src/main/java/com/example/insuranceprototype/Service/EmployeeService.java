package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.Employee;
import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Repository.EmployeeRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private ErrorService errorService;

    public List<Employee> getallEmployees(){
        return empRepo.findAll();
    }

    public Optional<Employee> getempById(Long id){
        return empRepo.findById(id);
    }

    public String addEmployee(Employee employee){
        empRepo.save(employee);
        return  errorService.getErrorById("ER001");
    }

    public List<Employee> globalSearch(String key){
        return empRepo.globalSearch(key);
    }

    public String updateEmpDetails(Long id , Employee employee){
        Employee emp = empRepo.getById(id);

        if(employee.getEmployeeId() != null){
            emp.setEmployeeId(employee.getEmployeeId());
        }
        if(employee.getEmployeeName() != null){
            emp.setEmployeeName(employee.getEmployeeName());
        }
        if(employee.getEmployeeEmail() != null){
            emp.setEmployeeEmail(employee.getEmployeeEmail());
        }
        if(employee.getEmployeeDesignation() != null){
            emp.setEmployeeDesignation(employee.getEmployeeDesignation());
        }
        empRepo.save(emp);
        return errorService.getErrorById("ER003");
    }

    public List<PersonalDetails> todaysInterview(Long id){
        Employee employee = empRepo.getById(id);
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<PersonalDetails> todaysCandidates = new ArrayList<>();
        for(int i=0; i<employee.getAssignedCandidates().size(); i++){
            if(employee.getAssignedCandidates().get(i).getAvailableDateAndTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).equals(data)){
                todaysCandidates.add(employee.getAssignedCandidates().get(i));
            }
        }
        return todaysCandidates;
    }

    public List<PersonalDetails> upcomingCandidates(Long id){
        Employee employee = empRepo.getById(id);
        LocalDate data = LocalDate.now();
        List<PersonalDetails> upcomingCandidates = new ArrayList<>();
        for(int i=0; i<employee.getAssignedCandidates().size(); i++){
            if(employee.getAssignedCandidates().get(i).getAvailableDateAndTime().toLocalDate().isAfter(data)){
                upcomingCandidates.add(employee.getAssignedCandidates().get(i));
            }
        }
        return upcomingCandidates;
    }

    public List<PersonalDetails> getPassedAssignedCandidates(Long id){
        Employee employee = empRepo.getById(id);
        String str = "passed";
        List<PersonalDetails> passedCandidates = new ArrayList<>();
        for(int i = 0; i<employee.getAssignedCandidates().size(); i++){
            if(employee.getAssignedCandidates().get(i).getCurrentStatus().toLowerCase().equals(str)){
                passedCandidates.add(employee.getAssignedCandidates().get(i));
            }
        }
        return  passedCandidates;
    }


    public long getPassed(Long id){
        Employee employee = empRepo.getById(id);
        long passed = 0;
        for(int i =0; i<employee.getAssignedCandidates().size(); i++){
            if(employee.getAssignedCandidates().get(i).getResult().equals("Passed")){
                passed += 1;
            }
        }
        return passed;
    }

    public long getFailed(Long id){
        Employee employee = empRepo.getById(id);
        long failed = 0;
        for(int i =0; i<employee.getAssignedCandidates().size(); i++){
            if(employee.getAssignedCandidates().get(i).getResult().equals("Failed")){
                failed += 1;
            }
        }
        return failed;
    }
}
