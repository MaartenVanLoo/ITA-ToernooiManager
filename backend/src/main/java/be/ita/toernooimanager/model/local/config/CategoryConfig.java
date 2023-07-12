package be.ita.toernooimanager.model.local.config;

import be.ita.toernooimanager.utils.Exclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
public class CategoryConfig {

    @Id
    @Exclude //Exclude from gson serialization
    private UUID id = UUID.randomUUID();

    private String category;
    private String competitionName;
    private Integer lowerWeight;
    private Integer upperWeight;
    private Integer matchTime;
    private String gender;
    private Integer pinTimeWazaAri;
    private Integer pinTimeIppon;


    @Version
    private Long version;
}
