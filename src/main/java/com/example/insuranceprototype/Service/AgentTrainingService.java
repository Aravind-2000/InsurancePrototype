package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.AgentTrainingDTO;
import com.example.insuranceprototype.Entity.AgentTrainingDetails;
import com.example.insuranceprototype.Entity.Training;
import com.example.insuranceprototype.Entity.TrainingDTO;
import com.example.insuranceprototype.Repository.AgentTrainingRepository;
import com.example.insuranceprototype.Repository.TrainingRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentTrainingService {


    @Autowired
    private TrainingDTOService trainingDTOService;

    @Autowired
    private AgentTrainingRepository agentTrainingRepo;

    @Autowired
    private ErrorService errorService;


    public List<AgentTrainingDTO> getAllTraineeAgents(){

        List<AgentTrainingDTO> answer = new ArrayList<>();
        List<AgentTrainingDetails> answerGot =  agentTrainingRepo.getAllValidTraineeAgents();

        answerGot.forEach(agentTrainingDetails -> {
            AgentTrainingDTO agentTrainingDTO = mapEntityToDTO(agentTrainingDetails);
            answer.add(agentTrainingDTO);
        });
        return answer;
    }


    public AgentTrainingDTO mapEntityToDTO(AgentTrainingDetails agentTrainingDetails){
        AgentTrainingDTO result = new AgentTrainingDTO();
        result.setId(agentTrainingDetails.getId());
        result.setTrainingId(agentTrainingDetails.getTrainingId());
        result.setTraining(trainingDTOService.mapEntityToDto(agentTrainingDetails.getTraining()));
        result.setAgentId(agentTrainingDetails.getAgentId());
        result.setAgent(agentTrainingDetails.getAgent());
        result.setIsApproved(agentTrainingDetails.getIsApproved());
        result.setApprovedBy(agentTrainingDetails.getApprovedBy());
        result.setApprovedByAgent(agentTrainingDetails.getApprovedByAgent());
        result.setApprovedDate(agentTrainingDetails.getApprovedDate());
        result.setTotalDays(agentTrainingDetails.getTotalDays());
        result.setDaysAttended(agentTrainingDetails.getDaysAttended());
        result.setTrainingScore(agentTrainingDetails.getTrainingScore());
        result.setTrainingStatus(agentTrainingDetails.getTrainingStatus());
        result.setComments(agentTrainingDetails.getComments());
        return result;
    }

    public AgentTrainingDTO getTraineeAgent(Long id){
         AgentTrainingDetails agentTrainingDetails =  agentTrainingRepo.getById(id);
         return mapEntityToDTO(agentTrainingDetails);
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

    public List<AgentTrainingDTO> getMyTrainings(Long agentid){
        List<AgentTrainingDetails> agentTrainingDetails =  agentTrainingRepo.getMyTrainings(agentid);
        List<AgentTrainingDTO> answer = new ArrayList<>();
        agentTrainingDetails.forEach(agentTrainingDetails1 -> {
            AgentTrainingDTO agentTrainingDTO = mapEntityToDTO(agentTrainingDetails1);
            answer.add(agentTrainingDTO);
        });
        return answer;
    }

}
