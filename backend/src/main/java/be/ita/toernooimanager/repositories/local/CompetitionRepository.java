package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    Optional<Competition> findByName(String name);

}
