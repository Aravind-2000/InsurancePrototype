package com.example.insuranceprototype.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class DumpRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long serviceId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceId", insertable = false, updatable = false)
    private Service service;

    private String methodName;


    @Size(max = 100000, min = 0)
    private String dumpValue;

}
