

package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    boolean existsByDirectorName(String directorName);

    // boolean existsBydirectorName(String getdirectorName);
}
