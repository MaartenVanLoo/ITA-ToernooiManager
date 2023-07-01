package be.ita.toernooimanager.service.local.acl;

import be.ita.toernooimanager.model.local.acl.Privilege;
import be.ita.toernooimanager.model.local.acl.Role;
import be.ita.toernooimanager.repositories.local.acl.RoleRepository;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class RoleService {
    RoleRepository roleRepository;
    PrivilegeService privilegeService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Role createRole(Role role) throws AlreadyExistsException {
        if (role.getName() == null) return null;
        if (roleRepository.existsByName(role.getName()))
            throw new AlreadyExistsException("Role: " + role.getName());
        if (role.getPrivileges() != null)
            role.setPrivileges(
                role.getPrivileges().stream()
                        .map(privilege -> privilegeService.getPrivilegeByName(privilege.getName()))
                        .collect(Collectors.toSet())
            );
        return roleRepository.save(role);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Role createRole(String name, String description, Set<Privilege> privileges) throws AlreadyExistsException {
        Role role = new Role(name, description, privileges);
        return this.createRole(role);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Role %s doesn't exist", name)));
    }
}
