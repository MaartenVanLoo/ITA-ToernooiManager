package be.ita.toernooimanager.model.local.config;

import be.ita.toernooimanager.utils.Exclude;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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
