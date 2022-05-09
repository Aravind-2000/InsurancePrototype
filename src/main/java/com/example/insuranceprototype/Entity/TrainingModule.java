package com.example.insuranceprototype.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TrainingModule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainingCostId;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trainingCostId", insertable = false, updatable = false)
    private TrainingCost trainingCost;

    private String trainingTopic;
    private String trainingDesc;

    private Long noOfDays;
    private String trainingLevel;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime createdDate;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;



}
