package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.AgentTypeLevel;
import com.example.insuranceprototype.Repository.AgentLevelRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/agentType")
@CrossOrigin
@RestController
public class AgentTypeController {

    @Autowired
    private AgentLevelRepository agentLevelRepository;

    @Autowired
    private ErrorService errorService;

    @GetMapping("/getall")
    public List<AgentTypeLevel> getAllTypes(){
        return agentLevelRepository.getAllActiveTypes();
    }

    @GetMapping("/{id}")
    public AgentTypeLevel getType(@PathVariable Long id){
        return agentLevelRepository.findById(id).get();
    }

    @PostMapping("/add")
    public String addType(@RequestBody AgentTypeLevel type){
        agentLevelRepository.save(type);
        return errorService.getErrorById("ER001");
    }

    @PatchMapping("/{id}")
    public String updateType(@PathVariable Long id, @RequestBody AgentTypeLevel type){

        AgentTypeLevel agentTypeLevel = agentLevelRepository.getById(id);
        if(type.getAgentLevelId() != null){
            agentTypeLevel.setAgentLevelId(type.getAgentLevelId());
        }
        if(type.getAgentLevelDesc() != null){
            agentTypeLevel.setAgentLevelDesc(type.getAgentLevelDesc());
        }
        agentLevelRepository.save(agentTypeLevel);
        return errorService.getErrorById("ER003");
    }

    @PatchMapping("/softDelete/{id}")
    public String softDelete(@PathVariable Long id){
        AgentTypeLevel agentTypeLevel = agentLevelRepository.getById(id);
        agentTypeLevel.setIsValid(-1);
        agentLevelRepository.save(agentTypeLevel);
        return errorService.getErrorById("ER003");
    }
}
