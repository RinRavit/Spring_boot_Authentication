package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    
    boolean existsByNoContract(String noContract);
}

