package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.RoleRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SuperAdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperAdminService(UserRepository userRepository, RoleRepository roleRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void assignRoleToUser(Long userId, String roleName) {
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

public User createUserBySuperAdmin(Map<String, Object> requestBody) {
    String username = (String) requestBody.get("username");
    String password = (String) requestBody.get("password");
    String email = (String) requestBody.get("email");
    String englishName = (String) requestBody.get("englishName");
    String khmerName = (String) requestBody.get("khmerName");
    String gender = (String) requestBody.get("gender");
    String countryCode = (String) requestBody.get("countryCode");
    String phoneNumber = (String) requestBody.get("phoneNumber");
    String province = (String) requestBody.get("province");
    String district = (String) requestBody.get("district");
    String commune = (String) requestBody.get("commune");

    if (userRepository.findByUsername(username).isPresent()) {
        throw new RuntimeException("Username already exists");
    }

    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setEnglishName(englishName);
    user.setKhmerName(khmerName);
    user.setGender(gender);
    user.setCountryCode(countryCode);
    user.setPhoneNumber(phoneNumber);
    user.setProvince(province);
    user.setDistrict(district);
    user.setCommune(commune);

    List<String> roleNames = (List<String>) requestBody.get("roles");
    if (roleNames == null || roleNames.isEmpty()) {
        throw new RuntimeException("At least one role must be specified");
    }

    Set<Role> roles = roleNames.stream()
        .map(roleName -> roleRepository.findByName(roleName)
            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
        .collect(Collectors.toSet());

    user.setRoles(roles);
    return userRepository.save(user);
}


}
