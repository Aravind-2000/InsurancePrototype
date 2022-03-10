package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Service.AgentAppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
@CrossOrigin
public class AgentController {

    @Autowired
    private AgentAppoinmentService agentService;


    @GetMapping("/getall")
    public List<AgentAppointmentDetails> getallagents(){
        return agentService.getAllAgent();
    }

    @GetMapping("/{id}")
    public AgentAppointmentDetails getAgent(@PathVariable Long id){
        return agentService.getAgentById(id);
    }

    @GetMapping("/search/{val}")
    public List<AgentAppointmentDetails> globalSearch(@PathVariable String val){
        return agentService.search(val);
    }

    @PostMapping("/add")
    public String addagent(@RequestBody AgentAppointmentDetails agent){
        return agentService.addAgent(agent);
    }

    @PatchMapping("/{id}")
    public String updateAgent(@PathVariable Long id,@RequestBody AgentAppointmentDetails agent ){
        return agentService.updateAgent(id, agent);
    }
    @PatchMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id){
        return agentService.delete(id);
    }
}
