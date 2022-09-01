package com.example.insuranceprototype.Entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime modifiedTime;



}
