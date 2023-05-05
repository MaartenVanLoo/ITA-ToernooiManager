package be.ita.toernooimanager.service.acl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    /*public HashMap<String, User> users = new HashMap<>();

    private RoleService roleService;

    public User createUser(User user) throws AlreadyExistsException {
        if (users.containsKey(user.getName())) {
            throw new AlreadyExistsException(String.format("User %s already exists",user.getName()));
        }
        Set<Role> roles = new HashSet<>();
        for (Role userRole: user.getRoles()){
            Role role = roleService.getRoleByName(userRole.getName());
            if (role == null) continue;
            roles.add(role);
        }
        user.setRoles(roles);
        users.put(user.getName(),user); //Make sure they reference the correct roles;
        return user;
    }
    public User createUser(String name, String description, Set<Role> userRoles) throws AlreadyExistsException {
        User user = new User(name, description, userRoles); //correct role mapping will happen here
        return this.createUser(user);
    }
    public User createUser(String name, String description, Role userRole) throws AlreadyExistsException {
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        User user = new User(name, description, roles);
        return this.createUser(user); //correct role mapping will happen here
    }

    public User getUserByName(String name) {
        return users.get(name);
    }

     */
}
