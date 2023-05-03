package be.ita.toernooimanager.repositories;

import be.ita.toernooimanager.model.MatchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatchesRepository extends JpaRepository<MatchesEntity,Integer> {
}
