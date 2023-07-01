package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Pool;
import be.ita.toernooimanager.model.local.Tree;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PoolRepository extends MongoRepository<Pool, UUID> {
}
