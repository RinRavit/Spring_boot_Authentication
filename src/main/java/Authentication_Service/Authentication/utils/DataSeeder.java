
package Authentication_Service.Authentication.utils;

import Authentication_Service.Authentication.entity.Permission;
import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.PermissionRepository;
import Authentication_Service.Authentication.repository.RoleRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository, UserRepository userRepository,
                      PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        try {
            seedPermissions();
            seedRoles();
            seedSuperAdmin();
            seedAdmin();
        } catch (Exception e) {
            System.err.println("Error during seeding process: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void seedPermissions() {
        if (permissionRepository.count() == 0) {
            System.out.println("Seeding permissions...");
            Permission manageUsers = new Permission();
            manageUsers.setName("MANAGE_USERS");
            permissionRepository.save(manageUsers);

            Permission readUsers = new Permission();
            readUsers.setName("READ_USERS");
            permissionRepository.save(readUsers);

            Permission writeUsers = new Permission();
            writeUsers.setName("WRITE_USERS");
            permissionRepository.save(writeUsers);

            Permission managePages = new Permission();
            managePages.setName("MANAGE_PAGES");
            permissionRepository.save(managePages);

            System.out.println("Permissions seeded successfully.");
        }
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            System.out.println("Seeding roles...");
            Permission manageUsers = permissionRepository.findByName("MANAGE_USERS").orElseThrow();
            Permission readUsers = permissionRepository.findByName("READ_USERS").orElseThrow();
            Permission writeUsers = permissionRepository.findByName("WRITE_USERS").orElseThrow();
            Permission managePages = permissionRepository.findByName("MANAGE_PAGES").orElseThrow();

            Role superAdminRole = new Role();
            superAdminRole.setName("SUPER_ADMIN");
            superAdminRole.setPermissions(Set.of(manageUsers, readUsers, writeUsers, managePages));
            roleRepository.save(superAdminRole);

            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setPermissions(Set.of(managePages));
            roleRepository.save(adminRole);

            System.out.println("Roles seeded successfully.");
        }
    }

    private void seedSuperAdmin() {
        if (userRepository.findByUsername("superadmin").isEmpty()) {
            System.out.println("Seeding SUPER_ADMIN account...");
            Role superAdminRole = roleRepository.findByName("SUPER_ADMIN").orElseThrow();
            User superAdmin = new User();
            superAdmin.setUsername("superadmin");
            superAdmin.setEmail("superadmin@example.com");
            superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
            superAdmin.setRoles(Set.of(superAdminRole));

            userRepository.save(superAdmin);
            System.out.println("SUPER_ADMIN account created successfully.");
        } else {
            System.out.println("SUPER_ADMIN account already exists.");
        }
    }

    private void seedAdmin() {
        if (userRepository.findByUsername("adminuser").isEmpty()) {
            System.out.println("Seeding ADMIN account...");
            Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            User adminUser = new User();
            adminUser.setUsername("adminuser");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("adminpassword"));
            adminUser.setRoles(Set.of(adminRole));

            userRepository.save(adminUser);
            System.out.println("ADMIN account created successfully.");
        } else {
            System.out.println("ADMIN account already exists.");
        }
    }
}
