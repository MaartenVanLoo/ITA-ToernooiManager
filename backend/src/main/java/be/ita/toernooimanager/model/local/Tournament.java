package be.ita.toernooimanager.model.local;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
public class Tournament {

    @Id
    private UUID id;

    //@Column(nullable = false)
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    public Tournament(@NotBlank @NotEmpty String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
