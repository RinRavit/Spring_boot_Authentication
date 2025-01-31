package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Role;
import Authentication_Service.Authentication.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final RoleRepository roleRepository;

    public AdminService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
}