package be.ita.toernooimanager.model.local;

import be.ita.toernooimanager.model.local.config.CompetitionConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Sub competition of tournament. Examples are: Tournament 2023 contains Competition U18, Competition U15, ...
 */

@Getter
@Setter
@Entity
public class Competition {
    @Id
    private UUID id = UUID.randomUUID();

    //@Column(nullable = false)
    private String name;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> birthYears;

    private UUID tournamentId = null;

    private boolean isRunning = false;  // Competition is running (or started) (can be used as "guard" before accessing information)
    private boolean isWeighing = false; // Weighing can be started separate from the actual competition (pre-weighing). Can be used to control the data accessed by the weight computer.


    //Allocation of tatamis
    @ManyToMany(fetch = FetchType.LAZY)
    List<Tatami> tatamis;


    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Competition fromConfig(Tournament tournament, CompetitionConfig config, String competitionName){
        Competition competition = new Competition();
        //TODO: load from config
        int currentYear = Year.now().getValue();
        competition.birthYears = config.getYears().stream().map((year) -> year + currentYear - config.getReferenceYear()).collect(Collectors.toList());
        competition.tournamentId = tournament.getId();
        competition.name = competitionName;
        return competition;
    }
}


