package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompetitorRepository extends JpaRepository<Competitor, UUID> {
    Optional<Competitor> findByWeightId(Integer weightId);
}
