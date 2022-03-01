package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Email.EmailService;
import com.example.insuranceprototype.Entity.Employee;
import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Entity.Status;
import com.example.insuranceprototype.Repository.DetailsRepository;
import com.example.insuranceprototype.Repository.EmployeeRepository;
import com.example.insuranceprototype.pdf.PdfService;
import com.itextpdf.layout.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository detailsRepo;

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfService pdfService;

    public List<PersonalDetails> getAllDetails(){
        return detailsRepo.findAll();
    }

    public String saveDetails(PersonalDetails details) throws FileNotFoundException {
        details.setCurrentStatus("Captured");
        details.setCreatedTime(LocalDateTime.now());
        details.setModifiedTime(LocalDateTime.now());
        detailsRepo.save(details);
        String path = pdfService.pdfConfirmation(details.getId());
        String body = " Hi " + details.getName() +  " Thank you for applying with our company. \n Shortly you will receive a email from us regarding your interview \n Thank You.";
        String sub = "Confirmation Email";
        emailService.sendMail(details.getEmail(), sub, body,path);
        return "Candidate ID " + details.getId() + " details saved successfully";
    }

    public Optional<PersonalDetails> getByCandidateId(Long id){
        return detailsRepo.findById(id);
    }

    public String deleteDetails(Long id ){
        detailsRepo.deleteById(id);
        return "Candidate ID " + id + " details deleted successfully";
    }

    public String updateDetails(Long id , PersonalDetails details) throws FileNotFoundException {

        PersonalDetails pd = detailsRepo.getById(id);

        if(details.getName() != null){
            pd.setName(details.getName());
        }
        if(details.getEmail() != null){
            pd.setEmail(details.getEmail());
        }
        if(details.getMobileNumber() != null){
            pd.setMobileNumber(details.getMobileNumber());
        }
        if(details.getDateOfBirth() != null){
            pd.setDateOfBirth(details.getDateOfBirth());
        }
        if(details.getHighestQualification() != null){
            pd.setHighestQualification(details.getHighestQualification());
        }
        if(details.getProof() != null){
            pd.setProof(details.getProof());
        }
        if(details.getProofId() != null){
            pd.setProofId(details.getProofId());
        }
        if(details.getCommunication() != null){
            pd.setCommunication(details.getCommunication());
        }
        if(details.getAvailableDateAndTime() != null){
            pd.setAvailableDateAndTime((details.getAvailableDateAndTime()));
        }
        if(details.getQuants() != null){
            pd.setQuants(details.getQuants());
        }
        if(details.getEmployee() != null){
            pd.setEmployee(details.getEmployee());
            pd.setCurrentStatus("Assigned");
            String b1 = " Hi " + pd.getName() +  " This mail is  regarding about your Interview. \n your Interview is assigned on " + pd.getAvailableDateAndTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy 'at' HH:mm "))+ "  \n Thank You.";
            String s1 = "Interview Call Letter";
            String path = pdfService.pdfCallLetter(pd.getId());
            emailService.sendMail(pd.getEmail(), s1, b1, path);
            Employee emp = empRepo.getById(details.getEmployee());
            String b2 = " Hi " + emp.getEmployeeName() +  "\n A Candidate's interview has been assigned to you on " + pd.getAvailableDateAndTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy 'at' HH:mm ")) + " \n Thank you";
            String s2 = " Interview Assigned Notification ";
            emailService.sendEmail(emp.getEmployeeEmail(), s2, b2);
        }
        pd.setModifiedTime(LocalDateTime.now());
        detailsRepo.save(pd);
        return "Candidate ID " + id + " updated successfully";
    }

    public List<PersonalDetails> getnamelike(String name){
       return detailsRepo.getNameLike(name);
    }
    public List<PersonalDetails> getEmailLike(String email){
        return detailsRepo.getEmailLike(email);
    }
    public List<PersonalDetails> getProofLike(String proof){
        return detailsRepo.getProofLike(proof);
    }

    public List<PersonalDetails> getcurrentstatuslike(String val){
        return detailsRepo.getCurrentStatusLike(val);
    }

    public List<PersonalDetails> searchAll(String val){
        return detailsRepo.searchbyAll(val);
    }
}
