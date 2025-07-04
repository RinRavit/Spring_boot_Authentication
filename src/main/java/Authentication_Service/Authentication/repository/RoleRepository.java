package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Optional<Role> findByNamel(Long name);
    Optional<Role> findByName(String name);

}
