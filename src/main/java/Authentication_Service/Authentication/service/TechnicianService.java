package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Technician;
import Authentication_Service.Authentication.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public Technician createTechnician(Technician technician) throws IOException {
        if (technicianRepository.existsByNameEnglish(technician.getNameEnglish())) {
            throw new RuntimeException("Technician already exists");
        }

        if (technician.getImage() != null && technician.getImage().startsWith("data:image")) {
            String base64Image = technician.getImage().split(",")[1];
            String imagePath = saveImageFromBase64(base64Image);
            technician.setImage(imagePath);
        }

        return technicianRepository.save(technician);
    }

    private String saveImageFromBase64(String base64Image) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String uploadDir = "uploads/technicians/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = UUID.randomUUID().toString() + ".png";
        File imageFile = new File(uploadDir + fileName);
        java.nio.file.Files.write(imageFile.toPath(), imageBytes);

        return "/uploads/technicians/" + fileName;
    }

    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll();
    }

    public Technician getTechnicianById(String id) {
        return technicianRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Technician updateTechnician(String id, Technician updated) throws IOException {
        Technician existing = getTechnicianById(id);

        existing.setNameEnglish(updated.getNameEnglish());
        existing.setNameKhmer(updated.getNameKhmer());
        existing.setGender(updated.getGender());
        existing.setTypeTechnical(updated.getTypeTechnical());
        existing.setPhone(updated.getPhone());

        if (updated.getImage() != null && updated.getImage().startsWith("data:image")) {
            String base64Image = updated.getImage().split(",")[1];
            String imagePath = saveImageFromBase64(base64Image);
            existing.setImage(imagePath);
        }

        return technicianRepository.save(existing);
    }

    public void deleteTechnician(String id) {
        technicianRepository.deleteById(id);
    }
}
