package Authentication_Service.Authentication.utils;

import Authentication_Service.Authentication.entity.Permission;
import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.repository.PermissionRepository;
import Authentication_Service.Authentication.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public DataSeeder(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            // Create permissions
            Permission readPermission = new Permission();
            readPermission.setName("READ");

            Permission writePermission = new Permission();
            writePermission.setName("WRITE");

            permissionRepository.save(readPermission);
            permissionRepository.save(writePermission);

            // Create roles
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setPermissions(Set.of(readPermission, writePermission));

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            userRole.setPermissions(Set.of(readPermission));

            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            System.out.println("Database Seeded with Roles and Permissions!");
        }
    }
}
