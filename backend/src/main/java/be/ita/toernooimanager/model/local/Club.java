package be.ita.toernooimanager.model.local;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    @Column
    @NotNull
    @NotBlank
    private String clubName;

    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> aliases = new ArrayList<>(); //Known aliases of club

    public Club(String clubName) {
        this.clubName = clubName;
    }
}
