
// // // // package Authentication_Service.Authentication.utils;


// // // // import java.util.Optional;
// // // // import java.util.Set;

// // // // import org.springframework.boot.CommandLineRunner;
// // // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // // import org.springframework.stereotype.Component;

// // // // import Authentication_Service.Authentication.entity.Role;
// // // // import Authentication_Service.Authentication.entity.User;
// // // // import Authentication_Service.Authentication.repository.RoleRepository;
// // // // import Authentication_Service.Authentication.repository.UserRepository;

// // // // @Component
// // // // public class DataSeeder implements CommandLineRunner {

// // // //     private final RoleRepository roleRepository;
// // // //     private final UserRepository userRepository;
// // // //     private final PasswordEncoder passwordEncoder;

// // // //     public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
// // // //         this.roleRepository = roleRepository;
// // // //         this.userRepository = userRepository;
// // // //         this.passwordEncoder = passwordEncoder;
// // // //     }

// // // //     @Override
// // // //     public void run(String... args) {
// // // //         System.out.println("Seeding roles and superadmin user...");
// // // //         seedRoles();
// // // //         seedSuperAdmin();
// // // //     }

// // // //     private void seedRoles() {
// // // //         if (roleRepository.count() == 0) {
// // // //             Role superAdminRole = new Role();
// // // //             superAdminRole.setName("ROLE_SUPER_ADMIN");
// // // //             roleRepository.save(superAdminRole);

// // // //             Role adminRole = new Role();
// // // //             adminRole.setName("ROLE_ADMIN");
// // // //             roleRepository.save(adminRole);

// // // //             Role userRole = new Role();
// // // //             userRole.setName("ROLE_USER");
// // // //             roleRepository.save(userRole);

// // // //             System.out.println("Roles seeded: SUPER_ADMIN, ADMIN, USER");
// // // //         }
// // // //     }

// // // //     private void seedSuperAdmin() {
// // // //         if (userRepository.count() == 0) {
// // // //             Optional<Role> superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
// // // //             if (superAdminRole.isEmpty()) {
// // // //                 throw new RuntimeException("ROLE_SUPER_ADMIN not found");
// // // //             }

// // // //             User superAdmin = new User();
// // // //             superAdmin.setUsername("superadmin");
// // // //             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
// // // //             superAdmin.setRoles(Set.of(superAdminRole.get()));

// // // //             userRepository.save(superAdmin);

// // // //             System.out.println("SuperAdmin user seeded");
// // // //         }
// // // //     }
// // // // }


// // // package Authentication_Service.Authentication.utils;

// // // import Authentication_Service.Authentication.entity.Role;
// // // import Authentication_Service.Authentication.entity.User;
// // // import Authentication_Service.Authentication.repository.RoleRepository;
// // // import Authentication_Service.Authentication.repository.UserRepository;
// // // import org.springframework.boot.CommandLineRunner;
// // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // import org.springframework.stereotype.Component;
// // // import java.util.Optional;
// // // import java.util.Set;

// // // @Component
// // // public class DataSeeder implements CommandLineRunner {

// // //     private final RoleRepository roleRepository;
// // //     private final UserRepository userRepository;
// // //     private final PasswordEncoder passwordEncoder;

// // //     public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
// // //         this.roleRepository = roleRepository;
// // //         this.userRepository = userRepository;
// // //         this.passwordEncoder = passwordEncoder;
// // //     }

// // //     @Override
// // //     public void run(String... args) {
// // //         seedRoles();
// // //         seedSuperAdmin();
// // //     }

// // //     private void seedRoles() {
// // //         if (roleRepository.count() == 0) {
// // //             roleRepository.save(new Role("SUPER_ADMIN"));
// // //             roleRepository.save(new Role("ADMIN"));
// // //             roleRepository.save(new Role("USER"));
// // //         }
// // //     }

// // //     private void seedSuperAdmin() {
// // //         if (userRepository.count() == 0) {
// // //             Optional<Role> superAdminRole = roleRepository.findByName("SUPER_ADMIN");
// // //             User superAdmin = new User();
// // //             superAdmin.setUsername("superadmin");
// // //             superAdmin.setEmail("superadmin@example.com");
// // //             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
// // //             superAdmin.setRoles(Set.of(superAdminRole.get()));

// // //             userRepository.save(superAdmin);
// // //         }
// // //     }
// // // }



// // package Authentication_Service.Authentication.utils;

// // import Authentication_Service.Authentication.entity.Permission;
// // import Authentication_Service.Authentication.entity.Role;
// // import Authentication_Service.Authentication.entity.User;
// // import Authentication_Service.Authentication.repository.PermissionRepository;
// // import Authentication_Service.Authentication.repository.RoleRepository;
// // import Authentication_Service.Authentication.repository.UserRepository;
// // import org.springframework.boot.CommandLineRunner;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.stereotype.Component;

// // import java.util.Optional;
// // import java.util.Set;

// // @Component
// // public class DataSeeder implements CommandLineRunner {

// //     private final RoleRepository roleRepository;
// //     private final UserRepository userRepository;
// //     private final PasswordEncoder passwordEncoder;
// //     private final PermissionRepository permissionRepository;

// //     public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, PermissionRepository permissionRepository) {
// //         this.roleRepository = roleRepository;
// //         this.userRepository = userRepository;
// //         this.passwordEncoder = passwordEncoder;
// //         this.permissionRepository = permissionRepository;
// //     }

// //     @Override
// //     public void run(String... args) {
// //         seedRoles();
// //         seedSuperAdmin();
// //     }

// //     private void seedRoles() {
// //         if (roleRepository.count() == 0) {
// //             Permission manageUsers = new Permission();
// //             manageUsers.setName("MANAGE_USERS");
// //             permissionRepository.save(manageUsers);
    
// //             Permission readUsers = new Permission();
// //             readUsers.setName("READ_USERS");
// //             permissionRepository.save(readUsers);
    
// //             Permission writeUsers = new Permission();
// //             writeUsers.setName("WRITE_USERS");
// //             permissionRepository.save(writeUsers);
    
// //             Role superAdminRole = new Role("SUPER_ADMIN");
// //             superAdminRole.setPermissions(Set.of(manageUsers, readUsers, writeUsers));
// //             roleRepository.save(superAdminRole);
    
// //             Role adminRole = new Role("ADMIN");
// //             adminRole.setPermissions(Set.of(readUsers, writeUsers));
// //             roleRepository.save(adminRole);
    
// //             Role userRole = new Role("USER");
// //             userRole.setPermissions(Set.of(readUsers));
// //             roleRepository.save(userRole);
    
// //             System.out.println("Roles seeded: SUPER_ADMIN, ADMIN, USER");
// //         }
// //     }
// //     private void seedSuperAdmin() {
// //         if (userRepository.count() == 0) {
// //             Optional<Role> superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
// //             if (superAdminRole.isEmpty()) {
// //                 throw new RuntimeException("ROLE_SUPER_ADMIN not found. Seed roles first!");
// //             }

// //             User superAdmin = new User();
// //             superAdmin.setUsername("superadmin");
// //             superAdmin.setEmail("superadmin@example.com");
// //             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
// //             superAdmin.setRoles(Set.of(superAdminRole.get()));

// //             userRepository.save(superAdmin);
// //         }
// //     }
// // }


// package Authentication_Service.Authentication.utils;

// import Authentication_Service.Authentication.entity.Role;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.RoleRepository;
// import Authentication_Service.Authentication.repository.UserRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import java.util.Optional;
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
//         seedRoles();
//         seedSuperAdmin();
//     }

//     private void seedRoles() {
//         if (roleRepository.count() == 0) {
//             System.out.println("Seeding roles...");
//             roleRepository.save(new Role("SUPER_ADMIN"));
//             roleRepository.save(new Role("ADMIN"));
//             roleRepository.save(new Role("USER"));
//         }
//     }

//     private void seedSuperAdmin() {
//         Optional<Role> superAdminRole = roleRepository.findByName("SUPER_ADMIN");
//         if (superAdminRole.isEmpty()) {
//             throw new RuntimeException("Role SUPER_ADMIN not found in database.");
//         }

//         if (userRepository.findByUsername("superadmin").isEmpty()) {
//             System.out.println("Seeding SUPER_ADMIN account...");
//             User superAdmin = new User();
//             superAdmin.setUsername("superadmin");
//             superAdmin.setEmail("superadmin@example.com");
//             superAdmin.setPassword(passwordEncoder.encode("superadminpassword"));
//             superAdmin.setRoles(Set.of(superAdminRole.get()));

//             userRepository.save(superAdmin);
//         } else {
//             System.out.println("SUPER_ADMIN account already exists.");
//         }
//     }
// }


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

import java.util.Optional;
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
        seedPermissions();
        seedRoles();
        seedSuperAdmin();
        seedAnotherSuperAdmin();
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
        }
    }

    // private void seedRoles() {
    //     if (roleRepository.count() == 0) {
    //         System.out.println("Seeding roles...");
    //         Permission manageUsers = permissionRepository.findByName("MANAGE_USERS").orElseThrow();
    //         Permission readUsers = permissionRepository.findByName("READ_USERS").orElseThrow();
    //         Permission writeUsers = permissionRepository.findByName("WRITE_USERS").orElseThrow();

    //         Role superAdminRole = new Role();
    //         superAdminRole.setName("SUPER_ADMIN");
    //         superAdminRole.setPermissions(Set.of(manageUsers, readUsers, writeUsers));
    //         roleRepository.save(superAdminRole);

    //         Role adminRole = new Role();
    //         adminRole.setName("ADMIN");
    //         adminRole.setPermissions(Set.of(readUsers, writeUsers));
    //         roleRepository.save(adminRole);

    //         Role userRole = new Role();
    //         userRole.setName("USER");
    //         userRole.setPermissions(Set.of(readUsers));
    //         roleRepository.save(userRole);
    //     }
    // }
    private void seedRoles() {
        if (roleRepository.count() == 0) {
            Permission manageUsers = permissionRepository.findByName("MANAGE_USERS").orElseThrow();
            Permission readUsers = permissionRepository.findByName("READ_USERS").orElseThrow();
            Permission writeUsers = permissionRepository.findByName("WRITE_USERS").orElseThrow();
    
            Role superAdminRole = new Role();
            superAdminRole.setName("SUPER_ADMIN");
            superAdminRole.setPermissions(Set.of(manageUsers, readUsers, writeUsers));
            roleRepository.save(superAdminRole);
    
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setPermissions(Set.of(readUsers, writeUsers));
            roleRepository.save(adminRole);
    
            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setPermissions(Set.of(readUsers));
            roleRepository.save(userRole);
    
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
        } else {
            System.out.println("SUPER_ADMIN account already exists.");
        }
    }

    private void seedAnotherSuperAdmin() {
        if (userRepository.findByUsername("superadmin2").isEmpty()) {
            System.out.println("Seeding another SUPER_ADMIN account...");
            Role superAdminRole = roleRepository.findByName("SUPER_ADMIN").orElseThrow();
            User anotherSuperAdmin = new User();
            anotherSuperAdmin.setUsername("superadmin2");
            anotherSuperAdmin.setEmail("superadmin2@example.com");
            anotherSuperAdmin.setPassword(passwordEncoder.encode("superadminpassword2"));
            anotherSuperAdmin.setRoles(Set.of(superAdminRole));

            userRepository.save(anotherSuperAdmin);
        } else {
            System.out.println("Another SUPER_ADMIN account already exists.");
        }
    }
}
