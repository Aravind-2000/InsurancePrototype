package com.example.insuranceprototype.Controller;

import com.example.insuranceprototype.Entity.OfficeStructure;
import com.example.insuranceprototype.Service.OfficeStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("officestructure")
public class OfficeStructureController {

    @Autowired
    private OfficeStructureService officeStructureService;

    @GetMapping("/getall")
    public List<OfficeStructure> get(){
        return officeStructureService.getAllActive();
    }

    @GetMapping("/getbranch/{id}")
    public List<OfficeStructure> getBR(@PathVariable Long id){
        return officeStructureService.getUpLevel(id);
    }

    @GetMapping("/getofficelevel/{id}")
    public List<OfficeStructure> getOL(@PathVariable Long id){
        return officeStructureService.getByOfficeLevel(id);
    }

    @GetMapping("/getuplevel/{id}")
    public Optional<OfficeStructure> getUP(@PathVariable Long id){
        return officeStructureService.getById(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody OfficeStructure officeStructure){
        return officeStructureService.addOffice(officeStructure);
    }

    @PatchMapping("/{id}")
    public String updateOffice(@PathVariable Long id, @RequestBody OfficeStructure officeStructure){
        return officeStructureService.updateOffice(id, officeStructure);
    }

    @PatchMapping("/softdelete/{id}")
    public String softDelete(@PathVariable Long id){
        return officeStructureService.deactivateCompany(id);
    }
}

