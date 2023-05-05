package be.ita.toernooimanager.repositories.local.acl;

import be.ita.toernooimanager.model.local.acl.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface RoleRepository extends JpaRepository<Role, UUID> {
}
