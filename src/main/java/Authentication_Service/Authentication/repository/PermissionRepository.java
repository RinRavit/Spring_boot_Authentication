package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Permission;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name); // Add this method

}
