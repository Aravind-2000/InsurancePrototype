package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Entity.Promotion_Demotion_Details;
import com.example.insuranceprototype.Repository.AgentAppointmentDetailsRepository;
import com.example.insuranceprototype.Repository.PromotionDemotionRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PromotionDemotionService {

    @Autowired
    private PromotionDemotionRepository promotionDemotionRepository;

    @Autowired
    private AgentAppointmentDetailsRepository agentRepo;



    @Autowired
    private ErrorService errorService;

    @Autowired
    private DumpRecordService dumpService;


    public String doPromotionOrDemotion(Promotion_Demotion_Details promotion_demotion_details, Long serviceId, String methodName) throws IOException {

//        backupAgentRepo.insertIntoBackupAgent(promotion_demotion_details.getAgentId());
        AgentAppointmentDetails agent = agentRepo.getValidAgent(promotion_demotion_details.getAgentId());
        dumpService.addDumpRecord(serviceId, methodName, agent);
        agent.setAgentType(promotion_demotion_details.getAgentTypeId());
        promotionDemotionRepository.save(promotion_demotion_details);
        agentRepo.save(agent);
        if(promotion_demotion_details.getPromoteOrDemote().equalsIgnoreCase("PROMOTION")){
            return errorService.getErrorById("ER009");
        }
        return errorService.getErrorById("ER010");
    }
}
