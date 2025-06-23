
package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Contractor;
import Authentication_Service.Authentication.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    public Contractor createContractor(Contractor contractor) {
        
        return contractorRepository.save(contractor);
    }

    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }
}


// package Authentication_Service.Authentication.service;

// import Authentication_Service.Authentication.entity.Contractor;
// import Authentication_Service.Authentication.repository.ContractorRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class ContractorService {

//     @Autowired
//     private ContractorRepository contractorRepository;

//     public Object createContractor(Contractor contractor) {
//         Optional<Contractor> existing = contractorRepository
//             .findByImageAndDirectorNameAndContractorAndCompanyNameAndPhone(
//                 contractor.getImage(),
//                 contractor.getDirectorName(),
//                 contractor.getContractor(),
//                 contractor.getCompanyName(),
//                 contractor.getPhone()
//             );

//         if (existing.isPresent()) {
//             return "The Contractor already exists";
//         }

//         return contractorRepository.save(contractor);
//     }

//     public List<Contractor> getAllContractors() {
//         return contractorRepository.findAll();
//     }
// }
