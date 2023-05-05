package be.ita.toernooimanager.service.acl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    /*public HashMap<String,Role> roles = new HashMap<>();

    public Role createRole(Role role) throws AlreadyExistsException {
        if (roles.containsKey(role.getName())) {
            throw new AlreadyExistsException(String.format("Role %s already exists",role.getName()));
        }
        roles.put(role.getName(),role);
        return role;
    }
    public Role createRole(String name, String description, Set<Privilege> privileges) throws AlreadyExistsException {
        Role role = new Role(name, description, privileges);
        return this.createRole(role);
    }

    public Role getRoleByName(String name) {
        return roles.get(name);
    }*/
}
