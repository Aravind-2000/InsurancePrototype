package com.example.insuranceprototype.Proofs;

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
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String proofName;

    private String proofID;

    private String proofPurpose;

    @Lob
    private String proofFile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long clientID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int isValid;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime modifiedTime;

}
