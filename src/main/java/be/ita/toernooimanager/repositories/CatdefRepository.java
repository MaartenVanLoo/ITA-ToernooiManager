package be.ita.toernooimanager.repositories;

import be.ita.toernooimanager.model.CatdefObject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatdefRepository extends JpaRepository<CatdefObject,Integer> {
}
