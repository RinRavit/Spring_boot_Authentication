
// package Authentication_Service.Authentication.utils;


// import java.util.Optional;
// import java.util.Set;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import Authentication_Service.Authentication.entity.Role;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.RoleRepository;
// import Authentication_Service.Authentication.repository.UserRepository;

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
//     }

//     private void seedSuperAdmin() {
//         if (userRepository.count() == 0) {
//             Optional<Role> superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
//             if (superAdminRole.isEmpty()) {
//                 throw new RuntimeException("ROLE_SUPER_ADMIN not found");
//             }

//             User superAdmin = new User();
//             superAdmin.setUsername("superadmin");
//             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
//             superAdmin.setRoles(Set.of(superAdminRole.get()));

//             userRepository.save(superAdmin);

//             System.out.println("SuperAdmin user seeded");
//         }
//     }
// }


package Authentication_Service.Authentication.utils;

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
        seedRoles();
        seedSuperAdmin();
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("SUPER_ADMIN"));
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
        }
    }

    private void seedSuperAdmin() {
        if (userRepository.count() == 0) {
            Optional<Role> superAdminRole = roleRepository.findByName("SUPER_ADMIN");
            User superAdmin = new User();
            superAdmin.setUsername("superadmin");
            superAdmin.setEmail("superadmin@example.com");
            superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
            superAdmin.setRoles(Set.of(superAdminRole.get()));

            userRepository.save(superAdmin);
        }
    }
}
