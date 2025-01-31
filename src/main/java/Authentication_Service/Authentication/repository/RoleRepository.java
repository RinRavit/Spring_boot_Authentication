package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);

}
