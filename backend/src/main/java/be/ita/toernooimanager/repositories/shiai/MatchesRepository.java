package be.ita.toernooimanager.repositories.shiai;

import be.ita.toernooimanager.model.shiai.MatchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatchesRepository extends JpaRepository<MatchesEntity,Integer> {
}
