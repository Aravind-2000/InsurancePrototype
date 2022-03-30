package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Repository.AgentAppointmentDetailsRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentAppoinmentService {

    @Autowired
    private AgentAppointmentDetailsRepository agentAppointmentDetailsRepository;

    @Autowired
    private ErrorService errorService;


    public List<AgentAppointmentDetails> getAllAgent(){
        return agentAppointmentDetailsRepository.getallValidAgents();
    }

    public AgentAppointmentDetails getAgentById(Long id){
        return agentAppointmentDetailsRepository.getValidAgent(id);
    }

    public String addAgent(AgentAppointmentDetails agentAppoinmentDetails){
        agentAppoinmentDetails.setValidFlag(1);
        agentAppointmentDetailsRepository.save(agentAppoinmentDetails);
        return errorService.getErrorById("ER001");
    }

    public List<AgentAppointmentDetails> search(String key){
        return agentAppointmentDetailsRepository.search(key);
    }

    public String updateAgent(Long id ,AgentAppointmentDetails agentAppoinmentDetails){
        AgentAppointmentDetails agent = agentAppointmentDetailsRepository.getById(id);

        if(agentAppoinmentDetails.getClientId() != null){
            agent.setClientId(agentAppoinmentDetails.getClientId());
        }
        if(agentAppoinmentDetails.getClient() != null){
            agent.setClient(agentAppoinmentDetails.getClient());
        }
        if(agentAppoinmentDetails.getClient() != null){
            agent.setClient(agentAppoinmentDetails.getClient());
        }
        if(agentAppoinmentDetails.getDateAppointed() != null){
            agent.setDateAppointed(agentAppoinmentDetails.getDateAppointed());
        }
        if(agentAppoinmentDetails.getPreviousAgent() != null){
            agent.setPreviousAgent(agentAppoinmentDetails.getPreviousAgent());
        }
        if(agentAppoinmentDetails.getPrevDateOfTermination() != null){
            agent.setPrevDateOfTermination(agentAppoinmentDetails.getPrevDateOfTermination());
        }
        if(agentAppoinmentDetails.getDistributionChannel() != null){
            agent.setDistributionChannel(agentAppoinmentDetails.getDistributionChannel());
        }
        if(agentAppoinmentDetails.getBranch() != null){
            agent.setBranch(agentAppoinmentDetails.getBranch());
        }
        if(agentAppoinmentDetails.getAreaCode() != null){
            agent.setAreaCode(agentAppoinmentDetails.getAreaCode());
        }
        if(agentAppoinmentDetails.getAgentType() != null){
            agent.setAgentType(agentAppoinmentDetails.getAgentType());
        }
        if(agentAppoinmentDetails.getPayMethod() != null){
            agent.setPayMethod(agentAppoinmentDetails.getPayMethod());
        }
        if(agentAppoinmentDetails.getPayFrequency() != null){
            agent.setPayFrequency(agentAppoinmentDetails.getPayFrequency());
        }
        if(agentAppoinmentDetails.getCurrencyType() != null){
            agent.setCurrencyType(agentAppoinmentDetails.getCurrencyType());
        }
        if(agentAppoinmentDetails.getMinimumAmount() != null){
            agent.setMinimumAmount(agentAppoinmentDetails.getMinimumAmount());
        }
        if(agentAppoinmentDetails.getBonusAllocation() != null){
            agent.setBonusAllocation(agentAppoinmentDetails.getBonusAllocation());
        }
        if(agentAppoinmentDetails.getBasicCommission() != null){
            agent.setBasicCommission(agentAppoinmentDetails.getBasicCommission());
        }
        if(agentAppoinmentDetails.getRenewalCommission() != null){
            agent.setRenewalCommission(agentAppoinmentDetails.getRenewalCommission());
        }
        if(agentAppoinmentDetails.getServicingCommission() != null){
            agent.setServicingCommission(agentAppoinmentDetails.getServicingCommission());
        }
        if(agentAppoinmentDetails.getCommissionClass() != null){
            agent.setCommissionClass(agentAppoinmentDetails.getCommissionClass());
        }
        if(agentAppoinmentDetails.getOfficeId() != null){
            agent.setOfficeId(agentAppoinmentDetails.getOfficeId());
        }
        if(agentAppoinmentDetails.getUpLevelAgentId() != null){
            agent.setUpLevelAgentId(agentAppoinmentDetails.getUpLevelAgentId());
        }
        agentAppointmentDetailsRepository.save(agent);
        return errorService.getErrorById("ER003");
    }

    public String delete(Long id){
        AgentAppointmentDetails agent = agentAppointmentDetailsRepository.getValidAgent(id);
        agent.setValidFlag(-1);
        agentAppointmentDetailsRepository.save(agent);
        return errorService.getErrorById("ER003");
    }

}
