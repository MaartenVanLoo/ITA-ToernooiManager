package be.ita.toernooimanager.repositories;

import be.ita.toernooimanager.model.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriesRepository extends JpaRepository<CategoriesEntity,Integer> {
}
