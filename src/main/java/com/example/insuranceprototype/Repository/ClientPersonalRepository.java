package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.ClientMaintainPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientPersonalRepository extends JpaRepository<ClientMaintainPersonal, Long> {

    @Query(value = "select * from client_maintain_personal where valid_flag = 1", nativeQuery = true)
    List<ClientMaintainPersonal> getallValidClients();

    @Query(value = "select * from client_maintain_personal where id = :clientid and valid_flag = 1 ", nativeQuery = true)
    ClientMaintainPersonal getClientById(Long clientid);

    @Query(value = "select * from client_maintain_personal where id = :key and valid_flag = 1 or given_name like %:key% and valid_flag = 1 or sur_name like %:key% and valid_flag = 1 or mobile_number like %:key%  and valid_flag = 1 or language like %:key% and valid_flag = 1  or marrital_status like %:key% " +
            "and valid_flag = 1", nativeQuery = true )
    List<ClientMaintainPersonal> globalSearch(String key);
}
