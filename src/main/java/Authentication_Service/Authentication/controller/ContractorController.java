package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Contractor;
import Authentication_Service.Authentication.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contractors")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

  
    // Create
    @PostMapping("/create")
    public ResponseEntity<?> createContractor(@RequestBody Contractor contractor) {
    try {
        Contractor saved = contractorService.createContractor(contractor);
        return ResponseEntity.ok(saved);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    // List all
    @GetMapping("/list")
    public ResponseEntity<List<Contractor>> getAllContractors() {
    List<Contractor> contractors = contractorService.getAllContractors();
    return ResponseEntity.ok(contractors);
    }


    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContractor(@PathVariable Long id, @RequestBody Contractor updatedContractor) {
    try {
        Contractor updated = contractorService.updateContractor(id, updatedContractor);
        return ResponseEntity.ok(updated);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    // List by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getContractorById(@PathVariable Long id) {
    try {
        Contractor contractor = contractorService.getContractorById(id);
        return ResponseEntity.ok(contractor);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContractor(@PathVariable Long id) {
    try {
        contractorService.deleteContractor(id);
        return ResponseEntity.ok("Contractor " + id + " has been deleted successfully.");
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
}
