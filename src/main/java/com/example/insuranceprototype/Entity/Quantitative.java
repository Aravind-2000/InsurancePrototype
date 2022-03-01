package com.example.insuranceprototype.Entity;


import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Quantitative {

    @Id
    private long id;

    private long numericalAbility;
    private String numericalAbilityComments;
    private long logicalReasoning;
    private String logicalReasoningComments;
    private long verbalAbility;
    private String verbalAbilityComments;
    private long codingAndDecoding;
    private String codingAndDecodingComments;
    private long overallRating;
    private String result;
    @Timestamp
    private LocalDateTime createdTime;

    @Timestamp
    private LocalDateTime modifiedTime;



}
