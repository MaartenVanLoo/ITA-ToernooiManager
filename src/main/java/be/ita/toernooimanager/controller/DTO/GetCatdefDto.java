package be.ita.toernooimanager.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCatdefDto {
    private Integer age;
    private String agetext;
    private Integer flags;
    private Integer weight;
    private String weighttext;
    private Integer matchtime;
    private Integer pintimekoka;
    private Integer pintimeyuko;
    private Integer pintimewazaari;
    private Integer pintimeippon;
    private Integer resttime;
    private Integer gstime;
    private Integer reptime;
    private String layout;
}
