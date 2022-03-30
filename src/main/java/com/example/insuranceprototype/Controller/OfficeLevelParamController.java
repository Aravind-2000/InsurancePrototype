package com.example.insuranceprototype.Controller;



import com.example.insuranceprototype.Entity.OfficeLevelParam;
import com.example.insuranceprototype.Repository.OfficeLevelParamRepo;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/officeLevel")
@CrossOrigin
@RestController
public class OfficeLevelParamController {

    @Autowired
    private OfficeLevelParamRepo officeLevelParamRepo;

    @Autowired
    private ErrorService errorService;

    @GetMapping("/getall")
    public List<OfficeLevelParam> getAllLevels(){
        return officeLevelParamRepo.getAllActiveLevels();
    }

    @GetMapping("/{id}")
    public OfficeLevelParam getLevel(@PathVariable Long id){
        return officeLevelParamRepo.getById(id);
    }

    @PostMapping("/add")
    public String addLevel(OfficeLevelParam level){
        level.setIsValid(1);
        officeLevelParamRepo.save(level);
        return errorService.getErrorById("ER001");
    }

    @PatchMapping("/{id}")
    public String updateType(@PathVariable Long id, @RequestBody OfficeLevelParam level){

        OfficeLevelParam officeLevelParam = officeLevelParamRepo.getById(id);
        if(level.getOfficeLevelId() != null){
            officeLevelParam.setOfficeLevelId(level.getOfficeLevelId());
        }
        if(level.getOfficeLevelDesc() != null){
            officeLevelParam.setOfficeLevelDesc(level.getOfficeLevelDesc());
        }
        officeLevelParamRepo.save(officeLevelParam);
        return errorService.getErrorById("ER003");
    }

    @PatchMapping("/softDelete/{id}")
    public String softDelete(@PathVariable Long id){
        OfficeLevelParam officeLevelParam = officeLevelParamRepo.getById(id);
        officeLevelParam.setIsValid(-1);
        officeLevelParamRepo.save(officeLevelParam);
        return errorService.getErrorById("ER003");
    }


}
