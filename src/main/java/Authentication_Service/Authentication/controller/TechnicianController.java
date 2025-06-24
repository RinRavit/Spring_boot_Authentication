package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Technician;
import Authentication_Service.Authentication.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @PostMapping("/create")
    public ResponseEntity<?> createTechnician(@RequestBody Technician technician) {
        try {
            return ResponseEntity.ok(technicianService.createTechnician(technician));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        return ResponseEntity.ok(technicianService.getAllTechnicians());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTechnicianById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(technicianService.getTechnicianById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTechnician(@PathVariable String id, @RequestBody Technician technician) {
        try {
            return ResponseEntity.ok(technicianService.updateTechnician(id, technician));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTechnician(@PathVariable String id) {
        try {
            technicianService.deleteTechnician(id);
            return ResponseEntity.ok("Technician deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
