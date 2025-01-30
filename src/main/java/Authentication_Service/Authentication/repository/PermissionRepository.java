package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
