package com.example.insuranceprototype.Notification;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String notificationType;

    private String notificationPriority;

    private Long employeeId;

    private Long candidateId;

    private String notificationText;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime modifiedTime;


    public Notification(String notificationType, String notificationPriority,   String notificationText, Long employeeId, Long candidateId) {
        this.notificationType = notificationType;
        this.notificationPriority = notificationPriority;
        this.employeeId = employeeId;
        this.candidateId = candidateId;
        this.notificationText = notificationText;
    }

    public Notification() {

    }
}
