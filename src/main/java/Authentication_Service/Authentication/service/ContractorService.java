

package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Contractor;
import Authentication_Service.Authentication.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    // Create Contractor
    public Contractor createContractor(Contractor contractor) throws IOException {
        if (contractorRepository.existsBydirectorName(contractor.getdirectorName())) {
            throw new RuntimeException("Contractor already exists");
        }
    
        if (contractor.getimage() != null && contractor.getimage().startsWith("data:image")) {
            String base64Image = contractor.getimage().split(",")[1]; // Remove "data:image/...;base64,"
            String imagePath = saveImageFromBase64(base64Image);
            contractor.setimage(imagePath);
        }
    
        return contractorRepository.save(contractor);
    }
    
    // Handle Upload Image
    private String saveImageFromBase64(String base64Image) throws IOException {
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
        String uploadDir = "uploads/contractors/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    
        String fileName = UUID.randomUUID().toString() + ".png";
        File imageFile = new File(uploadDir + fileName);
        java.nio.file.Files.write(imageFile.toPath(), imageBytes);
    
        return "/uploads/contractors/" + fileName;
    }


    // Update Contractor
    public Contractor updateContractor(String id, Contractor updatedContractor) throws IOException {
        Contractor existing = contractorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contractor not found"));
    
        existing.setdirectorName(updatedContractor.getdirectorName());
        existing.setcontractor(updatedContractor.getcontractor());
        existing.setcompanyName(updatedContractor.getcompanyName());
        existing.setlocation(updatedContractor.getlocation());
        existing.setphone(updatedContractor.getphone());
        existing.settypeService(updatedContractor.gettypeService());
        existing.settypeContract(updatedContractor.gettypeContract());
        existing.settypeContractor(updatedContractor.gettypeContractor());
    
        // If a new image is provided in base64
        if (updatedContractor.getimage() != null && updatedContractor.getimage().startsWith("data:image")) {
            String base64Image = updatedContractor.getimage().split(",")[1];
            String imagePath = saveImageFromBase64(base64Image);
            existing.setimage(imagePath);
        }
    
        return contractorRepository.save(existing);
    }
    

    // List all
    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }

    // List by ID
    public Contractor getContractorById(String id) {
        return contractorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contractor not found with ID: " + id));
    }
    
    // Detele
    public void deleteContractor(String id) {
        if (!contractorRepository.existsById(id)) {
            throw new RuntimeException("Contractor not found with ID: " + id);
        }
        contractorRepository.deleteById(id);
    }
    
    
}
