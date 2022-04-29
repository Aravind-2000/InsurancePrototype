package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.AgentTrainingDetails;
import com.example.insuranceprototype.Entity.Training;
import com.example.insuranceprototype.Repository.AgentTrainingRepository;
import com.example.insuranceprototype.Repository.TrainingRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AgentTrainingService {

    @Autowired
    private TrainingRepository trainingRepo;
    @Autowired
    private AgentTrainingRepository agentTrainingRepo;

    @Autowired
    private ErrorService errorService;


    public List<AgentTrainingDetails> getAllTraineeAgents(){
        return agentTrainingRepo.getAllValidTraineeAgents();
    }

    public AgentTrainingDetails getTraineeAgent(Long id){
        return agentTrainingRepo.getById(id);
    }

    public String addTraineeAgent(AgentTrainingDetails traineeAgent){
        traineeAgent.setValidFlag(1);
        agentTrainingRepo.save(traineeAgent);
        return errorService.getErrorById("ER001");
    }

    public String updateTraineeAgentDetails(Long id, AgentTrainingDetails traineeAgent){
        AgentTrainingDetails newDetails = agentTrainingRepo.getById(id);

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
        AgentTrainingDetails trainee = agentTrainingRepo.getById(id);
        trainee.setValidFlag(-1);
        agentTrainingRepo.save(trainee);
        return errorService.getErrorById("ER003");
    }

    public String deleteTraineeDetails(Long id){
        agentTrainingRepo.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<AgentTrainingDetails> getMyTrainings(Long agentid){
        return agentTrainingRepo.getMyTrainings(agentid);
    }

}
