package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Tatami;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TatamiRepository extends JpaRepository<Tatami, UUID> {
    Optional<Tatami> findByNumber(Integer number);
}
