package com.example.insuranceprototype.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Country {


    @Id
    private String Code;

    private String Capital;


}
