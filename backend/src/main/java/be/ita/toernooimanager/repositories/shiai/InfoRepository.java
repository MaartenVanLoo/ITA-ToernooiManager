package be.ita.toernooimanager.repositories.shiai;

import be.ita.toernooimanager.model.shiai.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InfoRepository extends JpaRepository<InfoEntity,Integer> {
}
