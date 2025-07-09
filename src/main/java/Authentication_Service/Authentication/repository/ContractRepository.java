package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contract;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    
    boolean existsByContractNo(String contractNo);
    Optional<Contract> findByProjectNoProject(@Param("projectNo") String projectNo);
}

