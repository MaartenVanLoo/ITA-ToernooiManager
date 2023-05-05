package be.ita.toernooimanager.service.acl;

import be.ita.toernooimanager.model.local.acl.Role;
import be.ita.toernooimanager.model.local.acl.User;
import be.ita.toernooimanager.repositories.local.acl.UserRepository;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private RoleService roleService;
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) throws AlreadyExistsException {
        if (userRepository.existsByName(user.getName())) {
            throw new AlreadyExistsException(String.format("User %s already exists",user.getName()));
        }
        if (user.getRoles() != null)
            user.setRoles(
                    user.getRoles().stream()
                            .map(role -> roleService.getRoleByName(role.getName()))
                            .collect(Collectors.toSet())
            );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User createUser(String name, String description, Set<Role> userRoles) throws AlreadyExistsException {
        User user = new User(name, description, userRoles);
        return this.createUser(user); //correct role mapping will happen here
    }
    public User createUser(String name, String description, Role userRole) throws AlreadyExistsException {
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        User user = new User(name, description, roles);
        return this.createUser(user); //correct role mapping will happen here
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User %s doesn't exist", name)));
    }

}
