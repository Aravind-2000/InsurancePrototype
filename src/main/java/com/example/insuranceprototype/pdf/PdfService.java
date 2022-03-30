package com.example.insuranceprototype.pdf;


import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Repository.DetailsRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service
public class PdfService {

    @Autowired
    private DetailsRepository detailsRepo;


    public String pdfConfirmation(Long id) throws FileNotFoundException {

        PersonalDetails det = detailsRepo.getById(id);
        String filepath = "src/main/resources/pdfFiles/" +det.getId() + " " + det.getName() + " - ApplicationForm.pdf";

            PdfWriter writer = new PdfWriter(new PdfWriter(filepath));

            PdfDocument pdfdoc = new PdfDocument(writer);
            pdfdoc.addNewPage();

            Document document = new Document(pdfdoc);
            document.add(new Paragraph("FuturaInstech Private Limited").setFontSize(16).setBold());
            document.add(new Paragraph("Address: 1st Main Rd	Judge Colony, Tambaram, Chennai, Tamil Nadu 600064").setFontSize(8).setBold());
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Candidate Details:-"));
            document.add(new Paragraph(" ID : " + det.getId()));
            document.add(new Paragraph(" Name : " + det.getName()));
            document.add(new Paragraph(" Date of Birth : " + det.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))));
            document.add(new Paragraph(" E-Mail : " + det.getEmail()));
            document.add(new Paragraph(" Mobile Number : " + det.getMobileNumber()));
            document.add(new Paragraph(" Highest Qualification : " + det.getHighestQualification()));
            document.add(new Paragraph(" Proof : " + det.getProof()));
            document.add(new Paragraph(" Proof ID : " + det.getProofId()));
            document.add(new Paragraph(" Communication Mode : " + det.getCommunication()));
            document.add(new Paragraph(" Available Date and Time Entered : " + det.getAvailableDateAndTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy 'at' HH:mm "))));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("This is a system generated message do not reply.").setFontSize(8).setItalic());
            document.close();
            return filepath;
    }

    public String pdfCallLetter(Long id) throws FileNotFoundException{

        PersonalDetails det = detailsRepo.getById(id);
        String filepath = "src/main/resources/pdfFiles/" +det.getId() + " " + det.getName() + " - Interview Call Letter.pdf";

        PdfWriter writer = new PdfWriter(filepath);

        PdfDocument callLetter = new PdfDocument(writer);
        callLetter.addNewPage();

        Document document = new Document(callLetter);

        document.add(new Paragraph("FuturaInstech Private Limited").setFontSize(16).setBold());
        document.add(new Paragraph("Address: 1st Main Rd	Judge Colony, Tambaram, Chennai, Tamil Nadu 600064").setFontSize(8).setBold());
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" Interview Call Letter :- "));
        document.add(new Paragraph(" Hi " + det.getName()+ " , Hope your doing good in this pandemic time. As you applied for the post developer trainee in our company, I am glad to inform that you have been scheduled an interview with us on " + det.getAvailableDateAndTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy 'at' HH:mm"))));
        document.add(new Paragraph("kindly attend your meeting in the below link "));
        document.add(new Paragraph(" Meet Link : https://meet.google.com/aei-dmoc-ynd"));
        document.add(new Paragraph("Thank you"));
        document.close();
        return filepath;
    }

    public Path uploadToDirectory(byte[] fileArray, String email) throws IOException{
        Path path = Paths.get(("src/main/resources/resumefiles/") + email);
        Files.write(path,fileArray);
        return path;
    }

}
