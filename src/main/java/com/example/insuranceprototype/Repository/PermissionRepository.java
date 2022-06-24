package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permissions, Long> {


    @Query(value = "select * from permissions where user_id = :userid and method = :method and service_id = :program", nativeQuery = true)
    List<Permissions> isMethodPresent(Long userid, Long program, String method);

    @Query(value = "select distinct method from permissions", nativeQuery = true)
    List<String> allPermissionMethods();


}
