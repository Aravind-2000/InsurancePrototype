package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.ClientAddressTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientAddressRepository extends JpaRepository<ClientAddressTable, Long> {

    @Query(value = "select * from client_address_table where valid_flag = 1", nativeQuery = true)
    List<ClientAddressTable> getallValidAddress();

    @Query(value = "select * from client_address_table where id = :addressid and valid_flag = 1", nativeQuery = true)
    ClientAddressTable getValidAddress(Long addressid);

    @Query(value = "select * from client_address_table where to_address like %:key% and valid_flag = 1 " +
            "or city like %:key% and valid_flag = 1 " +
            "or state like %:key% and valid_flag = 1 " +
            "or country like %:key% and valid_flag = 1 or address_type like %:key% and valid_flag = 1 ", nativeQuery = true)
    List<ClientAddressTable> globalSearch(String key);

}
