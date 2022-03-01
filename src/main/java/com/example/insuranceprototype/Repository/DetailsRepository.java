package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailsRepository extends JpaRepository<PersonalDetails, Long> {



    @Query(value = "select  * from personal_details where name like %:value% or  email  like %:value% or  proof  like %:value% or  current_status  like %:value% or result like %:value%" , nativeQuery = true)
    List<PersonalDetails> searchbyAll(String value);

    @Query(value = "select * from personal_details  where name like %:name% ", nativeQuery = true)
    List<PersonalDetails> getNameLike( String name);

    @Query(value = "select * from personal_details where email like %:email% ", nativeQuery = true)
    List<PersonalDetails> getEmailLike(String email);

    @Query(value = "select * from personal_details where proof like %:proof%", nativeQuery = true)
    List<PersonalDetails> getProofLike(String proof);

    @Query(value = "select * from personal_details where current_status like %:currentstatus%", nativeQuery = true)
    List<PersonalDetails> getCurrentStatusLike(String currentstatus);
}
