package com.example.insuranceprototype.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class OfficeLevelParam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long officeLevelId;

    private String officeLevelDesc;

}
