package com.example.insuranceprototype.error;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorRepo;

    public String getErrorById(String id){
        return errorRepo.getErrorByErrorId(id);
    }

}
