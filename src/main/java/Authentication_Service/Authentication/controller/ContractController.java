

package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Contract;
import Authentication_Service.Authentication.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;
      @Autowired
    private ObjectMapper objectMapper; 

    @PostMapping("/create")
public ResponseEntity<?> createContract(@RequestBody Map<String, Object> requestBody) {
    try {
        String projectNo = requestBody.get("projectNo").toString();
        // Convert map to Contract object manually or use a DTO + mapper
        Contract contract = objectMapper.convertValue(requestBody, Contract.class);
        return ResponseEntity.ok(contractService.createContract(contract, projectNo));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contract contract, @RequestParam String projectNo) {
        try {
            return ResponseEntity.ok(contractService.updateContract(id, contract, projectNo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contractService.getContractById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            contractService.deleteContract(id);
            return ResponseEntity.ok("Contract deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
