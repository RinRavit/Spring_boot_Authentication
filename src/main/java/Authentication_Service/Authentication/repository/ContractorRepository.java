package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contractor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractorRepository extends MongoRepository<Contractor, String> {
    boolean existsBydirectorName(String directorName);
    
}
