package be.ita.toernooimanager.model.local;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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

    @Version
    private Long version;

    /**
     * createdAt & updatedAt don't work in mongodb
     */
    //@Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Tournament(@NotBlank @NotEmpty String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
