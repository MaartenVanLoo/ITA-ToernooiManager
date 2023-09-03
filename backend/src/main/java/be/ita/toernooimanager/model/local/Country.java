package be.ita.toernooimanager.model.local;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country implements  Comparable<Country> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    @Column
    @NotNull
    @NotBlank
    private String countryName;

    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> aliases = new ArrayList<>(); //Known aliases of country

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public int compareTo(Country o) {
        return this.getCountryName().compareTo(o.getCountryName());
    }

    @Override
    public String toString() {
        return countryName;
    }
}
