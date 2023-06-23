package be.ita.toernooimanager.model.local;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String birtYear;

    @Column(name = "belt")
    private Integer belt; //kyu: <0; dan >0

    @Column
    @PositiveOrZero
    private Integer weight; //Value in grams

    @OneToOne
    private Club club;

    @Column(name = "country")
    private String country;
    @Column(name = "deleted")
    private Integer deleted; //TODO: Soft delete?

    @Column(name = "comment")
    private String comment;
}
