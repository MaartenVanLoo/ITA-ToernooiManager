package be.ita.toernooimanager.repositories.shiai;

import be.ita.toernooimanager.model.shiai.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriesRepository extends JpaRepository<CategoriesEntity,Integer> {
}
