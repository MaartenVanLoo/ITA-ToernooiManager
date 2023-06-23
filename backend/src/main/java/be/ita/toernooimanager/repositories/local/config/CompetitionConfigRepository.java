package be.ita.toernooimanager.repositories.local.config;

import be.ita.toernooimanager.model.local.config.CompetitionConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CompetitionConfigRepository extends MongoRepository<CompetitionConfig, UUID> {
}
