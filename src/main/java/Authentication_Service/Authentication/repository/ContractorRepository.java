package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contractor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractorRepository extends MongoRepository<Contractor, String> {
    // Optional<Contractor> findByImageAndDirectorNameAndContractorAndCompanyNameAndPhone(
    //     String image,
    //     String directorName,
    //     String contractor,
    //     String companyName,
    //     String location,
    //     String phone,
    //     String typeService,
    //     String typeContract,
    //     String typeContractor
    // );
    boolean existsBydirectorName(String directorName);
    
}
