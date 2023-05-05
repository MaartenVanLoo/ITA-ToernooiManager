package be.ita.toernooimanager.repositories.shiai;

import be.ita.toernooimanager.model.shiai.CatdefObject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatdefRepository extends JpaRepository<CatdefObject,Integer> {
}
