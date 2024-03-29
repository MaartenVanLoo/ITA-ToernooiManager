package be.ita.toernooimanager.model.local;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
public class Tree {
    @Id
    private UUID id = UUID.randomUUID();

    //@Column(nullable = false)
    private String name;

    private UUID categoryId;

    //@Column(nullable = false)
    @NotNull
    @NotBlank
    @NotEmpty
    private HashMap<String,UUID> tree = new HashMap<>();

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
