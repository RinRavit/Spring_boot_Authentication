package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
