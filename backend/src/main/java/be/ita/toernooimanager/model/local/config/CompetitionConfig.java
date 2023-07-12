package be.ita.toernooimanager.model.local.config;

import be.ita.toernooimanager.utils.Exclude;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class CompetitionConfig {

    @Id
    @Exclude //Exclude from gson serialization
    private UUID id = UUID.randomUUID();

    private String competitionName;
    private Integer referenceYear;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> years;

    @Version
    private Long version;
}
