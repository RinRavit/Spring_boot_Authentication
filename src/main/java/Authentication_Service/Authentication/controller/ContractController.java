package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Contract;
import Authentication_Service.Authentication.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping("/create")
    // ✅ Add security check here to restrict to admin
    public ResponseEntity<?> createContract(@RequestBody Contract contract) {
        try {
            Contract saved = contractService.createContract(contract);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContract(
        @PathVariable Long id,
        @RequestBody Contract updatedContract
    ) {
        try {
            Contract contract = contractService.updateContract(id, updatedContract);
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // List by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getContractById(@PathVariable Long id) {
        try {
            Contract contract = contractService.getContractById(id);
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable Long id) {
        try {
            contractService.deleteContract(id);
            return ResponseEntity.ok("Contract " + id + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
