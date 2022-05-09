package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.TrainingCost;
import com.example.insuranceprototype.Repository.TrainingCostRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingCostService {

    @Autowired
    private TrainingCostRepository costRepo;

    @Autowired
    private ErrorService errorService;


    public List<TrainingCost> getAllCosts(){
        return costRepo.getAllValids();
    }

    public TrainingCost getCost(Long id){
       return costRepo.getById(id);
    }

    public String addTrainingCost(TrainingCost cost){
        cost.setValidFlag(1);
        costRepo.save(cost);
        return errorService.getErrorById("ER001");
    }

    public String updateTrainingCost(Long id, TrainingCost newDetails){

        TrainingCost cost = costRepo.getById(id);

        if(newDetails.getBaseFee() != null){
            cost.setBaseFee(newDetails.getBaseFee());
        }

        if(newDetails.getTrainerFee() != null){
            cost.setTrainerFee(newDetails.getTrainerFee());
        }

        if(newDetails.getVenueFee() != null){
            cost.setVenueFee(newDetails.getVenueFee());
        }

        if(newDetails.getCurrency() != null){
            cost.setCurrency(newDetails.getCurrency());
        }

        if(newDetails.getPayBeforeDays() != null){
            cost.setPayBeforeDays(newDetails.getPayBeforeDays());
        }

        costRepo.save(cost);
        return errorService.getErrorById("ER003");
    }

    public String deactivateCost(Long id){
        TrainingCost cost = costRepo.getById(id);
        cost.setValidFlag(-1);
        costRepo.save(cost);
        return errorService.getErrorById("ER003");
    }
}
