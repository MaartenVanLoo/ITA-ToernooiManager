package be.ita.toernooimanager.model.local;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.eac.UnsignedInteger;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
    private String birthYear;

    @Column(name = "belt")
    @NotNull
    private Integer belt; //kyu: <0; dan >0

    @Column
    @PositiveOrZero
    private Integer weight; //Value in grams

    @OneToOne
    private Club club;

    @Column(name = "country")
    private String country;

    @Column(name = "deleted")
    private Boolean deleted = false; //TODO: Soft delete?

    @Column(name = "comment")
    private String comment;

    public Competitor(String firstName, String lastName, String birthYear, Integer belt, Club club, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.belt = belt;
        this.club = club;
        this.country = country;
    }
}
