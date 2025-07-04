package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    boolean existsByNameEnglish(String nameEnglish);
}
