package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.TrainingModule;
import com.example.insuranceprototype.Repository.TrainingModuleRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingModuleService {

    @Autowired
    private TrainingModuleRepository trainingModuleRepo;

    @Autowired
    private ErrorService errorService;


    public List<TrainingModule> getAllModules(){
        return trainingModuleRepo.getAllValids();
    }

    public TrainingModule getModule(Long id){
        return trainingModuleRepo.findById(id).get();
    }

    public List<TrainingModule> globalSearch(String val){
        return trainingModuleRepo.globalSearch(val);
    }

    public String addModule(TrainingModule module){
        module.setValidFlag(1);
        trainingModuleRepo.save(module);
        return errorService.getErrorById("ER001");
    }

    public String updateModule(Long id, TrainingModule newDetails){

        TrainingModule module = trainingModuleRepo.getById(id);

        if(newDetails.getTrainingCostId() != null){
            module.setTrainingCostId(newDetails.getTrainingCostId());
        }

        if(newDetails.getTrainingTopic() != null){
            module.setTrainingTopic(newDetails.getTrainingTopic());
        }

        if(newDetails.getTrainingDesc() != null){
            module.setTrainingDesc(newDetails.getTrainingDesc());
        }

        if(newDetails.getNoOfDays() != null){
            module.setNoOfDays(newDetails.getNoOfDays());
        }

        if(newDetails.getTrainingLevel() != null){
            module.setTrainingLevel(newDetails.getTrainingLevel());
        }

        trainingModuleRepo.save(module);
        return errorService.getErrorById("ER003");
    }

    public String deactivateModule(Long id){

        TrainingModule module = trainingModuleRepo.getById(id);
        module.setValidFlag(-1);
        trainingModuleRepo.save(module);
        return errorService.getErrorById("ER003");
    }



}
