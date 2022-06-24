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
public class TransactionJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agentId;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "agentId", updatable = false, insertable = false)
//    private AgentAppointmentDetails agent;

    private Long accountingRuleId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountingRuleId", insertable = false, updatable = false)
    private AccountingRule receiptReason;

    private Long transactionId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transactionId", updatable = false, insertable = false)
    private TransactionCode transactionCode;

    private Long originalCurrencyId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "originalCurrencyId", updatable = false, insertable = false)
    private CurrencyCode originalCurrencyCode;

    private Long accountCurrencyId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountCurrencyId", updatable = false, insertable = false)
    private CurrencyCode accountCurrencyCode;

    private Long receiptId;

    public String accountSign;

    public Double originalAmount;

    public Double accountAmount;

    public Long sequenceNo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;

}
