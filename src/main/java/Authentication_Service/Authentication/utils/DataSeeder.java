// package Authentication_Service.Authentication.utils;

// import Authentication_Service.Authentication.entity.Permission;
// import Authentication_Service.Authentication.entity.Role;
// import Authentication_Service.Authentication.repository.PermissionRepository;
// import Authentication_Service.Authentication.repository.RoleRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import java.util.Set;

// @Component
// public class DataSeeder implements CommandLineRunner {

//     private final RoleRepository roleRepository;
//     private final PermissionRepository permissionRepository;

//     public DataSeeder(RoleRepository roleRepository, PermissionRepository permissionRepository) {
//         this.roleRepository = roleRepository;
//         this.permissionRepository = permissionRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         if (roleRepository.count() == 0) {
//             // Create permissions
//             Permission readPermission = new Permission();
//             readPermission.setName("READ");

//             Permission writePermission = new Permission();
//             writePermission.setName("WRITE");

//             permissionRepository.save(readPermission);
//             permissionRepository.save(writePermission);

//             // Create roles
//             Role adminRole = new Role();
//             adminRole.setName("ADMIN");
//             adminRole.setPermissions(Set.of(readPermission, writePermission));

//             Role userRole = new Role();
//             userRole.setName("USER");
//             userRole.setPermissions(Set.of(readPermission));

//             roleRepository.save(adminRole);
//             roleRepository.save(userRole);

//             System.out.println("Database Seeded with Roles and Permissions!");
//         }
//     }
// }

package Authentication_Service.Authentication.utils;

// import Authentication_Service.Authentication.entity.Role;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.RoleRepository;
// import Authentication_Service.Authentication.repository.UserRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import java.util.Collections;
// import java.util.Set;

// @Component
// public class DataSeeder implements CommandLineRunner {

//     private final RoleRepository roleRepository;
//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//         this.roleRepository = roleRepository;
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @Override
//     public void run(String... args) {
//         if (roleRepository.count() == 0) {
//             Role superAdminRole = new Role();
//             superAdminRole.setName("SUPER_ADMIN");
//             roleRepository.save(superAdminRole);

//             Role userRole = new Role();
//             userRole.setName("USER");
//             roleRepository.save(userRole);

//             Role adminRole = new Role();
//             adminRole.setName("ADMIN");
//             roleRepository.save(adminRole);
//         }

//         if (userRepository.count() == 0) {
//             Role superAdminRole = roleRepository.findByName("SUPER_ADMIN")
//                     .orElseThrow(() -> new RuntimeException("SUPER_ADMIN not found"));

//             User superAdmin = new User();
//             superAdmin.setUsername("superadmin");
//             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
//             superAdmin.setRoles(Set.of(superAdminRole));

//             userRepository.save(superAdmin);
//         }
//     }
// }

// import Authentication_Service.Authentication.entity.Role;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.RoleRepository;
// import Authentication_Service.Authentication.repository.UserRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import java.util.Set;

// @Component
// public class DataSeeder implements CommandLineRunner {

//     private final RoleRepository roleRepository;
//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//         this.roleRepository = roleRepository;
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @Override
//     public void run(String... args) {
//         System.out.println("Seeding roles and superadmin user...");
//         seedRoles();
//         seedSuperAdmin();
//     }

//     private void seedRoles() {
//         if (roleRepository.count() == 0) {
//             Role superAdminRole = new Role();
//             superAdminRole.setName("ROLE_SUPER_ADMIN");
//             roleRepository.save(superAdminRole);

//             Role adminRole = new Role();
//             adminRole.setName("ROLE_ADMIN");
//             roleRepository.save(adminRole);

//             Role userRole = new Role();
//             userRole.setName("ROLE_USER");
//             roleRepository.save(userRole);

//             System.out.println("Roles seeded: SUPER_ADMIN, ADMIN, USER");
//         }
//         else {
//             System.out.println("Roles already exist.");
//         }
//     }

//     private void seedSuperAdmin() {
//         if (userRepository.count() == 0) {
//             Role superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN")
//                     .orElseThrow(() -> new RuntimeException("ROLE_SUPER_ADMIN not found"));

//             User superAdmin = new User();
//             superAdmin.setUsername("superadmin");
//             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
//             superAdmin.setRoles(Set.of(superAdminRole));

//             userRepository.save(superAdmin);

//             System.out.println("SuperAdmin user seeded");
//         }
//     }
// }

// package Authentication_Service.Authentication.utils;

import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.RoleRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        System.out.println("Seeding roles and superadmin user...");
        seedRoles();
        seedSuperAdmin();
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            Role superAdminRole = new Role();
            superAdminRole.setName("ROLE_SUPER_ADMIN");
            roleRepository.save(superAdminRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            System.out.println("Roles seeded: SUPER_ADMIN, ADMIN, USER");
        }
    }

    private void seedSuperAdmin() {
        if (userRepository.count() == 0) {
            Optional<Role> superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
            if (superAdminRole.isEmpty()) {
                throw new RuntimeException("ROLE_SUPER_ADMIN not found");
            }

            User superAdmin = new User();
            superAdmin.setUsername("superadmin");
            superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
            superAdmin.setRoles(Set.of(superAdminRole.get()));

            userRepository.save(superAdmin);

            System.out.println("SuperAdmin user seeded");
        }
    }
}
