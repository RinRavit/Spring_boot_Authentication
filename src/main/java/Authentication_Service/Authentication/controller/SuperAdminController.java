// // package Authentication_Service.Authentication.controller;

// // import org.springframework.web.bind.annotation.RestController;

// // import Authentication_Service.Authentication.RequestMapping;
// // import Authentication_Service.Authentication.service.SuperAdminService;
// // import Authentication_Service.Authentication.entity.User;

// // import org.springframework.web.bind.annotation.*;
// // import java.nio.file.AccessDeniedException;
// // import java.util.List;

// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.RequestParam;


// // @RestController
// // @RequestMapping("/Superadmin")
// // public class SuperAdminController {
// //     private final SuperAdminController superAdminService;;
// //     public SuperAdminController(SuperAdminService superAdminService) {
// //         this.superAdminService = superAdminService;
// //     }

// //     @GetMapping("/users")
// //    public ResponseEntity<?> getAllUsers(){
// //     try {
// //         List<User> users = superAdminService.getAllUsers();
// //         return ResponseEntity.ok(users);
// //     } catch (AccessDeniedException e){
// //         return ResponseEntity.status(403).body("Access denied" + e.getMessage());
// //     } catch (Exception e) {
// //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("🚨 Server Error: " + e.getMessage());
// //     }
// //     }
    
    
// // }


// package Authentication_Service.Authentication.controller;

// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.service.SuperAdminService;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

// @RestController
// @RequestMapping("/superadmin")
// public class SuperAdminController {

//     private final SuperAdminService superAdminService;

//     public SuperAdminController(SuperAdminService superAdminService) {
//         this.superAdminService = superAdminService;
//     }

//     @PreAuthorize("hasRole('SUPER_ADMIN')")
//     @GetMapping("/users")
//     public ResponseEntity<?> getAllUsers() {
//         try {
//             // Use the service layer to fetch users
//             List<User> users = superAdminService.getAllUsers();
//             return ResponseEntity.ok(users);
//         } catch (AccessDeniedException e) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
//         }
//     }
// }


// package Authentication_Service.Authentication.controller;

// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.service.SuperAdminService;

// import org.springframework.data.domain.Page;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/superadmin")
// public class SuperAdminController {

//     private final SuperAdminService superAdminService;

//     public SuperAdminController(SuperAdminService superAdminService) {
//         this.superAdminService = superAdminService;
//     }

//     @PreAuthorize("hasRole('SUPER_ADMIN')")
//     // @GetMapping("/users")
//     // public ResponseEntity<?> getAllUsers() {
//     //     try {
//     //         List<User> users = superAdminService.getAllUsers();
//     //         return ResponseEntity.ok(users);
//     //     } catch (AccessDeniedException e) {
//     //         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
//     //     } catch (Exception e) {
//     //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
//     //     }
//     // }
//     @GetMapping("/users")
// public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int page,
//                                      @RequestParam(defaultValue = "10") int size) {
//     try {
//         Page<User> users = superAdminService.getUsers(page, size);
//         return ResponseEntity.ok(users.getContent());
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
//     }
// }


//     @PreAuthorize("hasRole('SUPER_ADMIN')")
//     @PostMapping("/assign-role/{userId}")
//     public ResponseEntity<?> assignRoleToUser(@PathVariable String userId, @RequestBody String roleName) {
//         try {
//             superAdminService.assignRoleToUser(userId, roleName);
//             return ResponseEntity.ok("Role assigned successfully!");
//         } catch (AccessDeniedException e) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
//         }
//     }
// }


package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.service.SuperAdminService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    /**
     * Get all users with pagination.
     */
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<User> users = superAdminService.getUsers(page, size);
            return ResponseEntity.ok(users.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
        }
    }

    /**
     * Assign a role to a user.
     */
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/assign-role")
    public ResponseEntity<?> assignRoleToUser(@RequestBody Map<String, String> requestBody) {
        try {
            String userId = requestBody.get("userId");
            String roleName = requestBody.get("roleName");
            superAdminService.assignRoleToUser(userId, roleName);
            return ResponseEntity.ok("Role " + roleName + " assigned to user with ID " + userId + " successfully!");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
        }
    }
}
