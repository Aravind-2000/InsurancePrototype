package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.TrainingMappingDTO;
import com.example.insuranceprototype.Entity.TrainingMapping;
import com.example.insuranceprototype.Entity.TrainingModule;
import com.example.insuranceprototype.Entity.TrainingSession;
import com.example.insuranceprototype.Repository.TrainingMappingRepository;
import com.example.insuranceprototype.Repository.TrainingSessionRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingMappingService {


    @Autowired
    private TrainingDTOService trainingDTOService;

    @Autowired
    private TrainingMappingRepository agentTrainingRepo;

    @Autowired
    private TrainingSessionRepository trainingRepo;

    @Autowired
    private ErrorService errorService;


    public List<TrainingMappingDTO> getAllTraineeAgents(){

        List<TrainingMappingDTO> answer = new ArrayList<>();
        List<TrainingMapping> answerGot =  agentTrainingRepo.getAllValidTraineeAgents();

        answerGot.forEach(agentTrainingDetails -> {
            TrainingMappingDTO agentTrainingDTO = trainingDTOService.mappingToDTO(agentTrainingDetails);
            answer.add(agentTrainingDTO);
        });
        return answer;
    }


    public TrainingMappingDTO getTraineeAgent(Long id){
         TrainingMapping agentTrainingDetails =  agentTrainingRepo.getById(id);
         return trainingDTOService.mappingToDTO(agentTrainingDetails);
    }

    public String addTraineeAgent(TrainingMapping traineeAgent){
        traineeAgent.setValidFlag(1);
        TrainingSession trainingSession = trainingRepo.getById(traineeAgent.getTrainingId());
        calculateSponsoredAmount(traineeAgent, traineeAgent.getSponsoredPer(), trainingSession.getTrainingCost());
        agentTrainingRepo.save(traineeAgent);
        return errorService.getErrorById("ER001");
    }

    public void calculateSponsoredAmount(TrainingMapping agent, int percentage, Double totalCost){
        Double amount ;
        amount = totalCost * (double) percentage / 100 ;
        Double contribution = totalCost - amount;
        agent.setSponsoredAmount(amount);
        agent.setAgentContribution(contribution);
    }

    public String updateTraineeAgentDetails(Long id, TrainingMapping traineeAgent){
        TrainingMapping newDetails = agentTrainingRepo.getById(id);


        if(traineeAgent.getTrainingId() != null){
            newDetails.setTrainingId(traineeAgent.getTrainingId());
        }

        if(traineeAgent.getAgentId() != null){
            newDetails.setAgentId(traineeAgent.getAgentId());
        }

        if(traineeAgent.getIsApproved() != null){
            newDetails.setIsApproved(traineeAgent.getIsApproved());
        }

        if(traineeAgent.getApprovedBy() != null){
            newDetails.setApprovedBy(traineeAgent.getApprovedBy());
        }

        if(traineeAgent.getApprovedDate() != null){
            newDetails.setApprovedDate(traineeAgent.getApprovedDate());
        }

        if(traineeAgent.getTotalDays() != null){
            newDetails.setTotalDays(traineeAgent.getTotalDays());
        }

        if(traineeAgent.getDaysAttended() != null){
            newDetails.setDaysAttended(traineeAgent.getDaysAttended());
        }

        if(traineeAgent.getSponsoredBy() != null){
            newDetails.setSponsoredBy(traineeAgent.getSponsoredBy());
        }

        if(traineeAgent.getSponsoredPer() != 0){
            calculateSponsoredAmount(traineeAgent, traineeAgent.getSponsoredPer(), newDetails.getTraining().getTrainingCost());
            newDetails.setSponsoredPer(traineeAgent.getSponsoredPer());
        }

        if(traineeAgent.getSponsoredAmount() != null){
            newDetails.setSponsoredAmount(traineeAgent.getSponsoredAmount());
        }

        if(traineeAgent.getAgentContribution() != null){
            newDetails.setAgentContribution(traineeAgent.getAgentContribution());
        }

        if(traineeAgent.getPaymentStatus() != null){
            newDetails.setPaymentStatus(traineeAgent.getPaymentStatus());
        }

        if(traineeAgent.getTrainingScore() != null){
            newDetails.setTrainingScore(traineeAgent.getTrainingScore());
        }

        if(traineeAgent.getTrainingStatus() != null){
            newDetails.setTrainingStatus(traineeAgent.getTrainingStatus());
        }

        if(traineeAgent.getComments() != null){
            newDetails.setComments(traineeAgent.getComments());
        }

        agentTrainingRepo.save(newDetails);
        return errorService.getErrorById("ER003");
    }

    public String deactivateTrainee(Long id){
        TrainingMapping trainee = agentTrainingRepo.getById(id);
        trainee.setValidFlag(-1);
        agentTrainingRepo.save(trainee);
        return errorService.getErrorById("ER003");
    }

    public String deleteTraineeDetails(Long id){
        agentTrainingRepo.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<TrainingMappingDTO> getMyTrainings(Long agentid){
        List<TrainingMapping> agentTrainingDetails =  agentTrainingRepo.getMyTrainings(agentid);
        List<TrainingMappingDTO> answer = new ArrayList<>();
        agentTrainingDetails.forEach(agentTrainingDetails1 -> {
            TrainingMappingDTO agentTrainingDTO = trainingDTOService.mappingToDTO(agentTrainingDetails1);
            answer.add(agentTrainingDTO);
        });
        return answer;
    }

}
