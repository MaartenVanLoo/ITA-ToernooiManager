package be.ita.toernooimanager.model.local.config;

import be.ita.toernooimanager.utils.Exclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class CompetitionConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Exclude //Exclude from gson serialization
    private UUID id;
}
