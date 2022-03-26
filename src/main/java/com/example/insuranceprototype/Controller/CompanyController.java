package com.example.insuranceprototype.Controller;

import java.util.List;
import com.example.insuranceprototype.Entity.Company;
import com.example.insuranceprototype.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	

	@GetMapping("/getall")
	public List<Company> getAllActiveCompany(){
		return companyService.getAllActiveCompany();
	}

	@GetMapping("/{id}")
	public Company getCompany(@PathVariable Long id){
		return companyService.getActiveCompany(id);
	}

	@PostMapping("/add")
	public String addCompany(@RequestBody Company company) {
		return companyService.addCompany(company);
	}
	
	@PatchMapping("/{id}")
	public String updateCompany(@PathVariable Long id, @RequestBody Company company) {
		return companyService.updateCompany(id, company);
	}
	
	@PatchMapping("/delete/{id}")
	public String softDelete(@PathVariable Long id) {
		return companyService.deactivateCompany(id);	
	}
	
	@DeleteMapping("/{id}")
	public String deleteCompany(@PathVariable Long id) {
		return companyService.deleteCompany(id);
	}
	

}
