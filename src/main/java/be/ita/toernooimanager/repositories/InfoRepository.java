package be.ita.toernooimanager.repositories;

import be.ita.toernooimanager.model.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InfoRepository extends JpaRepository<InfoEntity,Integer> {
}
