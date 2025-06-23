// // package Authentication_Service.Authentication.repository;

// // import java.util.List;

// // import Authentication_Service.Authentication.entity.Contractor;

// // import org.springframework.data.mongodb.repository.MongoRepository;

// // public class ContractorRepository extends MongoRepository<Contractor, Long> {

//     // public Contractor save(Contractor contractor) {
//     //     // TODO Auto-generated method stub
//     //     throw new UnsupportedOperationException("Unimplemented method 'save'");
//     // }

//     // public List<Contractor> findAll() {
//     //     // TODO Auto-generated method stub
//     //     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     // }
    
// // }
  
// package Authentication_Service.Authentication.repository;

// import Authentication_Service.Authentication.entity.Contractor;

// import java.util.Optional;

// import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface ContractorRepository extends MongoRepository<Contractor, String> {

//     Optional<Contractor> findByDirectorNameAndContractorAndCompanyNameAndPhoneAndImage(String directorName,
//             String contractor, String companyName, String phone, String image);
// }

package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contractor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractorRepository extends MongoRepository<Contractor, String> {
    Optional<Contractor> findByImageAndDirectorNameAndContractorAndCompanyNameAndPhone(
        String image,
        String directorName,
        String contractor,
        String companyName,
        String phone
    );
    boolean existsByCompanyName(String companyName);
}
