package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competition;
import be.ita.toernooimanager.model.local.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClubRepository extends JpaRepository<Club, UUID> {

    Optional<Club> findByClubName(String clubName);
    Optional<Club> findByAliasesContainingIgnoreCase(String alias);
    Optional<Club> findByClubNameOrAliasesContainingIgnoreCase(String clubName, String alias);
}
