package be.ita.toernooimanager.model.local;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "weightId")
    @PositiveOrZero
    private Integer weightId = 0;

    //@ManyToOne
    //private Competition competition;

    @Column(name = "firstName")
    @NotNull
    @NotBlank
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    @NotBlank
    private String lastName;

    @Column(name = "birtYear")
    @NotNull
    private Integer birthYear;

    @Column(name = "belt")
    @NotNull
    private Integer belt; //kyu: <0; dan >0

    @Column
    @PositiveOrZero
    private Integer weight; //Value in grams

    @ManyToOne
    private Club club;

    @ManyToOne
    private Country country;

    @Column(name = "deleted")
    private Boolean deleted = false; //TODO: Soft delete?

    @Column(name = "comment")
    private String comment;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Competitor(String firstName, String lastName, Integer birthYear, Integer belt, Club club, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.belt = belt;
        this.club = club;
        this.country = country;
    }

    //Comparator to sort competitors
    //https://www.baeldung.com/java-sort-collection-multiple-fields
    public static Comparator<Competitor> createCompetitorLambdaComparator(){
        return Comparator.comparing(Competitor::getCountry)
                .thenComparing(Competitor::getClub);
    }
}
