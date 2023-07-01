package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TournamentRepository  extends MongoRepository<Tournament, UUID> {
}
