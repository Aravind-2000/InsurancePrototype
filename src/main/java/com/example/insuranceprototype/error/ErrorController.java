package com.example.insuranceprototype.error;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/error")
@CrossOrigin
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    @GetMapping("/{id}")
    public String geterrorbyid(@PathVariable String id ){
        return errorService.getErrorById(id);
    }
}
