// package Authentication_Service.Authentication.controller;

// import Authentication_Service.Authentication.entity.Contractor;
// import Authentication_Service.Authentication.service.ContractorService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/contractors")
// @PreAuthorize("hasRole('ADMIN')")
// public class ContractorController {

//     @Autowired
//     private ContractorService contractorService;

//     @PostMapping("/create")
//     public ResponseEntity<Contractor> createContractor(@RequestBody Contractor contractor) {
//         Contractor saved = contractorService.createContractor(contractor);
//         return ResponseEntity.ok(saved);
//     }

//     @GetMapping
//     public ResponseEntity<List<Contractor>> getAllContractors() {
//         return ResponseEntity.ok(contractorService.getAllContractors());
//     }
// }


package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Contractor;
import Authentication_Service.Authentication.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractors")
@PreAuthorize("hasRole('ADMIN')")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @PostMapping("/create")
    public ResponseEntity<?> createContractor(@RequestBody Contractor contractor) {
        Object result = contractorService.createContractor(contractor);

        if (result instanceof String) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Contractor>> getAllContractors() {
        return ResponseEntity.ok(contractorService.getAllContractors());
    }
}
