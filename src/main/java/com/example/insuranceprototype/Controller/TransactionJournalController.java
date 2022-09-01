package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.TransactionJournal;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.TransactionJournalService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactionjournal")
@CrossOrigin
public class TransactionJournalController {


    @Autowired
    private TransactionJournalService transactionJournalService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 21;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-transactionjournal";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(transactionJournalService.getAllTransJournal());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-transactionjournal";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(transactionJournalService.getTransJournal(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody TransactionJournal transactionJournal) {
        String method = "add-transactionjournal";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(transactionJournalService.addTransJournal(transactionJournal));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @RequestBody TransactionJournal transactionJournal,  @PathVariable Long id) {
        String method = "update-transactionjournal";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(transactionJournalService.updateTransJournal(id, transactionJournal));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "soft-delete-transactionjournal";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(transactionJournalService.softDeleteTransJournal(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/harddelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "hard-delete-transactionjournal";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(transactionJournalService.hardDeleteTransJournal(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(transactionJournalService.search(key));
    }

    @GetMapping("/agent/{agentid}")
    public ResponseEntity<?> uponAgent(@PathVariable Long agentid){
        return ResponseEntity.ok(transactionJournalService.basedUponAgent(agentid));
    }
}
