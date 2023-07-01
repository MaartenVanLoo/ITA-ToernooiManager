package be.ita.toernooimanager.model.local;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
public class Pool {
    @Id
    private UUID id = UUID.randomUUID();;

    //@Column(nullable = false)
    private String name;

    private UUID categoryId;

    //@Column(nullable = false)
    @NotNull
    @NotBlank
    @NotEmpty
    private HashMap<String,UUID> pool = new HashMap<>();
}
