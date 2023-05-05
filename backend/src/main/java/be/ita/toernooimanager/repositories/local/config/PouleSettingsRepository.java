package be.ita.toernooimanager.repositories.local.config;

import be.ita.toernooimanager.model.local.acl.Privilege;
import be.ita.toernooimanager.model.local.config.PouleSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PouleSettingsRepository extends JpaRepository<PouleSettings, UUID> {
}
