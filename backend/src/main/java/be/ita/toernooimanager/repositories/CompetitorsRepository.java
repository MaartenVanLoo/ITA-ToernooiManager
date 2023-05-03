package be.ita.toernooimanager.repositories;

import be.ita.toernooimanager.model.CompetitorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompetitorsRepository extends JpaRepository<CompetitorsEntity,Integer> {
}
