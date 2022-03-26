package com.example.insuranceprototype.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Getter
@Setter
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long companyId;

	private String companyName;

	private String companyCountry;

	private String companyLicenseNumber;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate companyLicenseIssueDate;

	private String companyCurrency;

	private String companyStatus;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int isValid;

	@CreationTimestamp
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDateTime companyCreatedDate;

	@UpdateTimestamp
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDateTime companyModifiedDate;

}
