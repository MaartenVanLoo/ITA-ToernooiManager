package be.ita.toernooimanager.security;

import be.ita.toernooimanager.model.local.acl.Privilege;
import be.ita.toernooimanager.model.local.acl.User;
import be.ita.toernooimanager.repositories.local.acl.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(()->
            new UsernameNotFoundException(String.format("User with name %s not found", name)));

        // Get list of user privileges
        Set<Privilege> privileges = user.getRoles().stream()
                .flatMap(role -> role.getPrivileges().stream()).collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> grantedAuthorities = privileges.stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                grantedAuthorities);
    }

}
