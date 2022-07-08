package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Repository.ServiceMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/methods")
@CrossOrigin
public class ServiceMethodController {


    @Autowired
    private ServiceMethodRepository repository;


    @GetMapping("/service/{id}")
    public ResponseEntity<?> getAll(@PathVariable Long id){
        return ResponseEntity.ok(repository.getByServiceId(id));
    }


}
