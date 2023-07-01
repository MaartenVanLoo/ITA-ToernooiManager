package be.ita.toernooimanager.model.local.config;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class CompetitionConfig {

    @Id
    private UUID id = UUID.randomUUID();

    private String competitionName;
    private Integer referenceYear;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> years;
}
