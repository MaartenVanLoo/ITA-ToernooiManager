package be.ita.toernooimanager.repositories.local.acl;


import be.ita.toernooimanager.model.local.acl.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    Optional<Privilege> findByName(String name);
    boolean existsPrivilegeByName(String name);
}
