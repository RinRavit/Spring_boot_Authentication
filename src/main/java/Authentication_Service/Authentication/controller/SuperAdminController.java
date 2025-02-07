

package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.service.SuperAdminService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    private final SuperAdminService superAdminService;
    // private final UserRepository userRepository;
    private final UserRepository userRepository;

    public SuperAdminController(SuperAdminService superAdminService, UserRepository userRepository) {
        this.superAdminService = superAdminService;
        // this.userRepository = userRepository;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        try {
            Page<User> users = superAdminService.getUsers(page, size);
            return ResponseEntity.ok(users.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/assign-role")
    public ResponseEntity<?> assignRoleToUser(@RequestBody Map<String, String> requestBody) {
        try {
            String userId = requestBody.get("userId");
            String roleName = requestBody.get("roleName");

            superAdminService.assignRoleToUser(userId, roleName);
            return ResponseEntity.ok("Role assigned successfully!");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error: " + e.getMessage());
        }
    }
    @PreAuthorize("hasRole('SUPER_ADMIN')")
@GetMapping("/role-count")
public ResponseEntity<?> getRoleCount() {
    try {
        Map<String, Long> roleCounts = superAdminService.getRoleCounts();
        return ResponseEntity.ok(roleCounts);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}

@PreAuthorize("hasRole('SUPER_ADMIN')")
@GetMapping("/list-admins")
public ResponseEntity<?> getAllAdmins() {
    List<User> admins = userRepository.findAll().stream()
        .filter(user -> user.getRoles().stream()
            .anyMatch(role -> role.getName().equals("ADMIN")))
        .collect(Collectors.toList());
    return ResponseEntity.ok(admins);
}

@PreAuthorize("hasRole('SUPER_ADMIN')")
@GetMapping("/list-users")
public ResponseEntity<?> getAllUsersWithOnlyUserRole() {
    try {
        List<User> users = userRepository.findAll().stream()
            .filter(user -> 
                user.getRoles().size() == 1 && 
                user.getRoles().stream().anyMatch(role -> role.getName().equals("USER"))
            ) // Filter users who only have the USER role
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error fetching users: " + e.getMessage());
    }
}


}
