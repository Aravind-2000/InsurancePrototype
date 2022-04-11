package com.example.insuranceprototype.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Permissions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long serviceId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceId", updatable = false, insertable = false)
    private Service service;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long roleId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", updatable = false, insertable = false)
    private Role role;

    private Long userId;

    private String method;


}
