package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentAppointmentDetailsRepository extends JpaRepository<AgentAppointmentDetails, Long> {

    @Query(value = "select * from agent_appointment_details where valid_flag = 1" , nativeQuery = true)
    List<AgentAppointmentDetails> getallValidAgents();

    @Query(value = "select * from agent_appointment_details where id = :agentid and valid_flag = 1", nativeQuery = true)
    AgentAppointmentDetails getValidAgent(Long agentid);


    @Query(value = "select * from agent_appointment_details where id = :key and valid_flag = 1 ", nativeQuery = true)
    List<AgentAppointmentDetails> search(String key);

    @Query(value = "select * from agent_appointment_details where valid_flag = -1", nativeQuery = true)
    List<AgentAppointmentDetails> getAllInvalidAgents();

    @Query(value = "select * from agent_appointment_details where office_id = :officeId and valid_flag = 1", nativeQuery = true)
    List<AgentAppointmentDetails> getAgentsByOffice(Long officeId);
}
