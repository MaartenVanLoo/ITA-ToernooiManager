package be.ita.toernooimanager.controller.DTO;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public class CompetitorGetDto {

    private UUID id;

    @PositiveOrZero
    private Integer weightId = 0;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    private Integer birthYear;

    @NotNull
    private Integer belt; //kyu: <0; dan >0

    @PositiveOrZero
    private Integer weight; //Value in grams

    private Club club;

    private Country country;
}
