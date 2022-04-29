package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Service;
import com.example.insuranceprototype.Repository.ServiceRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/service")
@CrossOrigin
@RestController
public class ServiceController {


    @Autowired
    private ServiceRespository serviceRespository;

    @GetMapping("/getall")
    public List<Service> getAllServices(){
        return serviceRespository.findAll();
    }
}
