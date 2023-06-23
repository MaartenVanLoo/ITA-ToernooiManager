package be.ita.toernooimanager.model.local;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Entity
@Getter
@Setter
@Document
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
