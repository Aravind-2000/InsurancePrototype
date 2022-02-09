package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.ParameterTable;
import com.example.insuranceprototype.Repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository paramRepo;


    public List<ParameterTable> getAllParams(){
        return paramRepo.findAll();
    }

    public List<String> getRuleOne(String rule){
        return paramRepo.getParameterwithRuleone(rule);
    }

}
