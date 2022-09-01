package com.example.insuranceprototype.Service;

import java.util.List;

import com.example.insuranceprototype.Entity.Company;
import com.example.insuranceprototype.Repository.CompanyRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ErrorService errorService;
	
	public List<Company> getAll(){
		return companyRepository.findAll();
	}
	public Company get(Long id) {
		return companyRepository.findById(id).get();
	}
	
	public List<Company> getAllActiveCompany(){
		return companyRepository.getValidFlag();
	}

	public Company getActiveCompany(Long id){
		return companyRepository.getCompany(id);
	}

	public List<Company> globalSearch(String key){
		return companyRepository.globalSearch(key);
	}

	
	public String addCompany(Company company) {
		company.setIsValid(1);
		companyRepository.save(company);
		return errorService.getErrorById("E0001");
	}
	
	public String deactivateCompany(Long companyId){

			Company company = companyRepository.getById(companyId);
			company.setIsValid(-1);
			companyRepository.save(company);
			return errorService.getErrorById("E0004");
		}
	
	public String updateCompany(Long companyId, Company company ) {

		Company os= companyRepository.getById(companyId);
		if(company.getCompanyName() != null) {
			os.setCompanyName(company.getCompanyName());
		}
		if(company.getCompanyCountry() != null) {
			os.setCompanyCountry(company.getCompanyCountry());
		}
		if(company.getCompanyLicenseNumber() != null) {
			os.setCompanyLicenseNumber(company.getCompanyLicenseNumber());
		}
		if(company.getCompanyLicenseIssueDate() != null) {
			os.setCompanyLicenseIssueDate(company.getCompanyLicenseIssueDate());
		}
		if(company.getCompanyCurrency() != null) {
			os.setCompanyCurrency(company.getCompanyCurrency());
		}
		if(company.getCompanyStatus() != null) {
			os.setCompanyStatus(company.getCompanyStatus());
		}
		companyRepository.save(os);
		return errorService.getErrorById("E0002");
	}

	public String deleteCompany(Long companyId) {
		if(companyRepository.findById(companyId).isPresent()) {
			companyRepository.deleteById(companyId);
			return errorService.getErrorById("E0003")+" "+companyId;
		}
		else {
			return errorService.getErrorById("E0005");
		}
		}
}
