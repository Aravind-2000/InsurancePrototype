package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.Training;
import com.example.insuranceprototype.Repository.TrainingRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {


    @Autowired
    private TrainingRepository trainingRepo;

    @Autowired
    private ErrorService errorService;



    public List<Training> getAllTrainings(){
        return trainingRepo.getAllValids();
    }

    public Training getTraining(Long id){
        return trainingRepo.findById(id).get();
    }

    public String addTraining(Training training){
        training.setValidFlag(1);
        trainingRepo.save(training);
        return errorService.getErrorById("ER001");
    }

    public String updateTraining(Long id , Training training){

        Training trainingDetails = trainingRepo.getById(id);

        if(training.getTrainingTopic()!= null){
            trainingDetails.setTrainingTopic(training.getTrainingTopic());
        }

        if(training.getTrainingDesc()!= null){
            trainingDetails.setTrainingDesc(training.getTrainingDesc());
        }

        if(training.getTrainingType()!= null){
            trainingDetails.setTrainingType(training.getTrainingType());
        }

        if(training.getTrainingMode()!= null){
            trainingDetails.setTrainingMode(training.getTrainingMode());
        }

        if(training.getTrainingLevel()!= null){
            trainingDetails.setTrainingLevel(training.getTrainingLevel());
        }

        if(training.getStartDate()!= null){
            trainingDetails.setStartDate(training.getStartDate());
        }

        if(training.getEndDate()!= null){
            trainingDetails.setEndDate(training.getEndDate());
        }

        if(training.getTrainingTime()!= null){
            trainingDetails.setTrainingTime(training.getTrainingTime());
        }

        if(training.getTrainer()!= null){
            trainingDetails.setTrainer(training.getTrainer());
        }

        if(training.getTrainingCost()!= null){
            trainingDetails.setTrainingCost(training.getTrainingCost());
        }

        if(training.getSponsoredBy()!= null){
            trainingDetails.setSponsoredBy(training.getSponsoredBy());
        }

        if(training.getContinuanceId()!= null){
            trainingDetails.setContinuanceId(training.getContinuanceId());
        }

        trainingRepo.save(trainingDetails);
        return errorService.getErrorById("ER003");
    }

    public String deactivateTraining(Long id){
        Training training = trainingRepo.getById(id);
        training.setValidFlag(-1);
        trainingRepo.save(training);
        return errorService.getErrorById("ER003");
    }

    public String deleteTraining(Long id){
        trainingRepo.deleteById(id);
        return errorService.getErrorById("ER003");
    }

}
