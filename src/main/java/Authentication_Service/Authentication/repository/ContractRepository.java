package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContractRepository extends MongoRepository<Contract, String> {
    
    boolean existsByNoContract(String noContract);
}

