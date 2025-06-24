package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Technician;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechnicianRepository extends MongoRepository<Technician, String> {
    boolean existsByNameEnglish(String nameEnglish);
}
