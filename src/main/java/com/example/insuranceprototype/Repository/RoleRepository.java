package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findByroleName(String roleName);


}
