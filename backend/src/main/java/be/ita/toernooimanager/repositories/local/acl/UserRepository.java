package be.ita.toernooimanager.repositories.local.acl;

import be.ita.toernooimanager.model.local.acl.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByName(String name);

    Optional<User> findByName(String name);
}
