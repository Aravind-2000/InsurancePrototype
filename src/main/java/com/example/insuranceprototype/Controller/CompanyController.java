package com.example.insuranceprototype.Controller;

import java.util.List;

import com.example.insuranceprototype.Auth.repository.UserRepository;
import com.example.insuranceprototype.Entity.Company;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.ClientService;
import com.example.insuranceprototype.Service.CompanyService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private ErrorService errorService;

	int programId = 5;
	

	@GetMapping("/getall/{userid}")
	public ResponseEntity<?> getAllActiveCompany(@PathVariable Long userid){
		String method = "get-all-company";
		if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
			return ResponseEntity.ok(companyService.getAllActiveCompany());
		}
		return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	}

	@GetMapping("/{id}/{userid}")
	public ResponseEntity<?> getCompany(@PathVariable Long id, @PathVariable Long userid){
		String method = "get-company";
		if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
			return ResponseEntity.ok(companyService.getActiveCompany(id));
		}
		return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
	}

	@PostMapping("/add/{userid}")
	public ResponseEntity<?> addCompany(@RequestBody Company company, @PathVariable Long userid) {
		String method = "add-company";
		if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
			return ResponseEntity.ok( companyService.addCompany(company));
		}
		return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
	}

	@PatchMapping("/{id}/{userid}")
	public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company, @PathVariable Long userid) {
		String method = "update-company";
		if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
			return ResponseEntity.ok(companyService.updateCompany(id, company));
		}
		return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
	}

	@PatchMapping("/delete/{id}/{userid}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userid) {
		String method = "soft-delete-company";
		if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
			return ResponseEntity.ok(companyService.deactivateCompany(id));
		}
		return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
	}

	@DeleteMapping("/{id}/{userid}")
	public ResponseEntity<?> deleteCompany(@PathVariable Long id, @PathVariable Long userid) {
		String method = "hard-delete-company";
		if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
			return ResponseEntity.ok(companyService.deleteCompany(id));
		}
		return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
	}

	@GetMapping("/search/{key}")
	public ResponseEntity<?> search(@PathVariable String key){
		return ResponseEntity.ok(companyService.globalSearch(key));
	}
}
