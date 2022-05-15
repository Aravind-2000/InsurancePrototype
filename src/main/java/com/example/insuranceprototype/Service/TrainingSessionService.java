package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.TrainingModule;
import com.example.insuranceprototype.Entity.TrainingSession;
import com.example.insuranceprototype.Repository.TrainingModuleRepository;
import com.example.insuranceprototype.Repository.TrainingSessionRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TrainingSessionService {


    @Autowired
    private TrainingSessionRepository trainingRepo;

    @Autowired
    private TrainingModuleRepository moduleRepo;

    @Autowired
    private ErrorService errorService;



    public List<TrainingSession> getAllTrainings(){
        return trainingRepo.getAllValids();
    }

    public TrainingSession getTraining(Long id){
        return trainingRepo.findById(id).get();
    }

    public String addTraining(TrainingSession training){
        training.setValidFlag(1);
        trainingCostCalculations(training);
        trainingRepo.save(training);
        return errorService.getErrorById("ER001");
    }

    public List<TrainingSession> globalSearch(String val){
        return trainingRepo.globalSearch(val);
    }

    public void trainingCostCalculations(TrainingSession training){
        TrainingModule module = moduleRepo.getById(training.getTrainingModuleId());

        if(training.getTrainingType().equalsIgnoreCase("external") && training.getTrainingMode().equalsIgnoreCase("offline")){
            training.setTrainingCost(module.getTrainingCost().getBaseFee() + module.getTrainingCost().getTrainerFee() + module.getTrainingCost().getVenueFee());
        }
        else if(training.getTrainingType().equalsIgnoreCase("external")){
            training.setTrainingCost(module.getTrainingCost().getBaseFee() + module.getTrainingCost().getTrainerFee());
        }
        else if (training.getTrainingMode().equalsIgnoreCase("offline")){
            training.setTrainingCost(module.getTrainingCost().getBaseFee() + module.getTrainingCost().getVenueFee());
        }
        else{
            training.setTrainingCost(module.getTrainingCost().getBaseFee());
        }
    }

    public String updateTraining(Long id , TrainingSession training){

        TrainingSession trainingDetails = trainingRepo.getById(id);
        TrainingModule module = moduleRepo.getById(trainingDetails.getTrainingModuleId());

        if(training.getTrainingCost()!= null){
            trainingDetails.setTrainingCost(training.getTrainingCost());
        }

        if(training.getTrainingModuleId() != null){
            trainingDetails.setTrainingModuleId(training.getTrainingModuleId());
        }

        if(training.getTrainingType()!= null){
            if(training.getTrainingType().equalsIgnoreCase(trainingDetails.getTrainingType())){
                trainingDetails.setTrainingType(training.getTrainingType());
            }
            else{
                if(training.getTrainingType().equalsIgnoreCase("internal")){
                    trainingDetails.setTrainingCost(trainingDetails.getTrainingCost() - module.getTrainingCost().getTrainerFee());
                }
                if(training.getTrainingType().equalsIgnoreCase("external")){
                    trainingDetails.setTrainingCost(trainingDetails.getTrainingCost() + module.getTrainingCost().getTrainerFee());
                }
                trainingDetails.setTrainingType(training.getTrainingType());
            }
        }

        if(training.getTrainingMode()!= null){
            if(training.getTrainingMode().equalsIgnoreCase(trainingDetails.getTrainingMode())){
                trainingDetails.setTrainingMode(training.getTrainingMode());
            }
            else{
                if(training.getTrainingMode().equalsIgnoreCase("online")){
                    trainingDetails.setTrainingCost(trainingDetails.getTrainingCost() - module.getTrainingCost().getVenueFee());
                }
                if(training.getTrainingMode().equalsIgnoreCase("offline")){
                    trainingDetails.setTrainingCost(trainingDetails.getTrainingCost() + module.getTrainingCost().getVenueFee());
                }
                trainingDetails.setTrainingMode(training.getTrainingMode());
            }
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

        if(training.getContinuanceId()!= null){
            trainingDetails.setContinuanceId(training.getContinuanceId());
        }

        trainingRepo.save(trainingDetails);
        return errorService.getErrorById("ER003");
    }

    public String deactivateTraining(Long id){
        TrainingSession training = trainingRepo.getById(id);
        training.setValidFlag(-1);
        trainingRepo.save(training);
        return errorService.getErrorById("ER003");
    }

    public String deleteTraining(Long id){
        trainingRepo.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public String dateValidation(String startDate, String endDate){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        if(start.isBefore(end)){
            return null;
        }
        else{
            return errorService.getErrorById("ER011");
        }
    }
}
