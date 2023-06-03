package be.ita.toernooimanager.model.local.config;

import be.ita.toernooimanager.utils.Exclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class PouleSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Exclude //Exclude from gson serialization
    private UUID id;

    private Integer competitorCount;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Column(nullable = false)
    @JoinTable(
            name="PouleMatchMapping",
            joinColumns = {@JoinColumn(name = "PouleSettings_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PouleMatch_id", referencedColumnName = "id", nullable = false)}
    )
    private List<PouleMatch> matches;
    @Column(nullable = false)
    private Integer matchCount;

    private String __comment__;

}

@Entity
class PouleMatch{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Exclude //Exclude from gson serialization
    private UUID id;
    @Column(nullable = false)
    private Integer white;
    private Integer blue;
    private Integer round;
}