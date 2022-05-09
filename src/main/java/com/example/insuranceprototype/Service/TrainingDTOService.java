package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.*;
import org.springframework.stereotype.Service;
@Service
public class TrainingDTOService {


    public TrainingMappingDTO mappingToDTO(TrainingMapping agentTrainingDetails){
        TrainingMappingDTO result = new TrainingMappingDTO();
        result.setId(agentTrainingDetails.getId());
        result.setTrainingId(agentTrainingDetails.getTrainingId());
        result.setTraining(mapEntityToDto(agentTrainingDetails.getTraining()));
        result.setAgentId(agentTrainingDetails.getAgentId());
        result.setAgent(agentTrainingDetails.getAgent());
        result.setIsApproved(agentTrainingDetails.getIsApproved());
        result.setApprovedBy(agentTrainingDetails.getApprovedBy());
        result.setApprovedByAgent(agentTrainingDetails.getApprovedByAgent());
        result.setApprovedDate(agentTrainingDetails.getApprovedDate());
        result.setTotalDays(agentTrainingDetails.getTotalDays());
        result.setDaysAttended(agentTrainingDetails.getDaysAttended());
        result.setSponsoredBy(agentTrainingDetails.getSponsoredBy());
        result.setSponsoredAmount(agentTrainingDetails.getSponsoredAmount());
        result.setSponsoredPer(agentTrainingDetails.getSponsoredPer());
        result.setAgentContribution(agentTrainingDetails.getAgentContribution());
        result.setPaymentStatus(agentTrainingDetails.getPaymentStatus());
        result.setTrainingScore(agentTrainingDetails.getTrainingScore());
        result.setTrainingStatus(agentTrainingDetails.getTrainingStatus());
        result.setComments(agentTrainingDetails.getComments());
        return result;
    }

    public TrainingSessionDTO mapEntityToDto(TrainingSession training) {
        TrainingSessionDTO result = new TrainingSessionDTO();
        result.setId(training.getId());
        result.setTrainingModule(mapModuleToDto(training.getTrainingModule()));
        result.setTrainingType(training.getTrainingType());
        result.setTrainingMode(training.getTrainingMode());
        result.setStartDate(training.getStartDate());
        result.setEndDate(training.getEndDate());
        result.setTrainingTime(training.getTrainingTime());
        result.setTrainer(training.getTrainer());
        result.setTrainingCost(training.getTrainingCost());
        return result;
    }

    public TrainingModuleDTO mapModuleToDto(TrainingModule module){

        TrainingModuleDTO result = new TrainingModuleDTO();
        result.setId(module.getId());
        result.setTrainingDesc(module.getTrainingDesc());
        result.setTrainingLevel(module.getTrainingLevel());
        result.setTrainingTopic(module.getTrainingTopic());
        result.setNoOfDays(module.getNoOfDays());
        return result;

    }


}
