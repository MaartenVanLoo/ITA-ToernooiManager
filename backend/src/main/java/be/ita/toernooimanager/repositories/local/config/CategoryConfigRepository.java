package be.ita.toernooimanager.repositories.local.config;

import be.ita.toernooimanager.model.local.config.CategoryConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryConfigRepository extends MongoRepository<CategoryConfig, UUID> {
    Optional<CategoryConfig> findByCategory(String category);
    Optional<CategoryConfig> findByCompetitionName(String competitionName);
}
