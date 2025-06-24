package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.RoleRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.utils.JwtUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SuperAdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public SuperAdminService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void assignRoleToUser(String userId, String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities() == null) {
            throw new AccessDeniedException("No authentication found.");
        }

        boolean isSuperAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SUPER_ADMIN"));

        if (!isSuperAdmin) {
            throw new AccessDeniedException("Access Denied: Only SUPER_ADMIN can assign roles.");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Role not found." + roleName);
        }

        user.getRoles().add(roleOptional.get());
        userRepository.save(user);
    }

     public Page<User> getUsers(int page, int size) {
        // Fetch paginated users from the database
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest);
    }
    public Map<String, Long> getRoleCounts() {
    Map<String, Long> roleCounts = new HashMap<>();
    long userCount = userRepository.findAll().stream()
            .filter(user -> user.getRoles().stream()
                    .anyMatch(role -> role.getName().equalsIgnoreCase("USER")))
            .count();

    long adminCount = userRepository.findAll().stream()
            .filter(user -> user.getRoles().stream()
                    .anyMatch(role -> role.getName().equalsIgnoreCase("ADMIN")))
            .count();

    roleCounts.put("USER", userCount);
    roleCounts.put("ADMIN", adminCount);
    return roleCounts;
}
}
