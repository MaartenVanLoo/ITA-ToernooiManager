package be.ita.toernooimanager.model.local;

import be.ita.toernooimanager.model.local.config.CompetitionConfig;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;
import java.util.List;
import java.util.UUID;


/**
 * Sub competition of tournament. Examples are: Tournament 2023 contains Competition U18, Competition U15, ...
 */

@Getter
@Setter
@Document
public class Competition {
    @Id
    private UUID id = UUID.randomUUID();

    //@Column(nullable = false)
    private String name;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> birthYears;

    private UUID tournamentId = null;

    private boolean isRunning = false;

    public static Competition fromConfig(Tournament tournament, CompetitionConfig config, String competitionName){
        Competition competition = new Competition();
        //TODO: load from config
        int currentYear = Year.now().getValue();
        competition.birthYears = config.getYears();
        for (Integer year : competition.birthYears){
            year += currentYear - config.getReferenceYear();
        }
        competition.tournamentId = tournament.getId();
        competition.name = competitionName;
        return competition;
    }
}


