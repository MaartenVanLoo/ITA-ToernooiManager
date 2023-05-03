package be.ita.toernooimanager.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AttributeOverrides({
        @AttributeOverride( name = "age", column = @Column(name = "age")),
        @AttributeOverride( name = "agetext", column = @Column(name = "agetext")),
        @AttributeOverride( name = "flags", column = @Column(name = "flags")),
        @AttributeOverride( name = "weight", column = @Column(name = "weight")),
        @AttributeOverride( name = "weighttext", column = @Column(name = "weighttext")),
        @AttributeOverride( name = "matchtime", column = @Column(name = "matchtime")),
        @AttributeOverride( name = "pintimekoka", column = @Column(name = "pintimekoka")),
        @AttributeOverride( name = "pintimeyuko", column = @Column(name = "pintimeyuko")),
        @AttributeOverride( name = "pintimewazaari", column = @Column(name = "pintimewazaari")),
        @AttributeOverride( name = "pintimeippon", column = @Column(name = "pintimeippon")),
        @AttributeOverride( name = "resttime", column = @Column(name = "resttime")),
        @AttributeOverride( name = "gstime", column = @Column(name = "gstime")),
        @AttributeOverride( name = "reptime", column = @Column(name = "reptime")),
        @AttributeOverride( name = "layout", column = @Column(name = "layout")),
})
@Getter
@Setter
public class CatdefEntity implements Serializable {
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatdefEntity that = (CatdefEntity) o;

        if (!Objects.equals(age, that.age)) return false;
        if (!Objects.equals(agetext, that.agetext)) return false;
        if (!Objects.equals(flags, that.flags)) return false;
        if (!Objects.equals(weight, that.weight)) return false;
        if (!Objects.equals(weighttext, that.weighttext)) return false;
        if (!Objects.equals(matchtime, that.matchtime)) return false;
        if (!Objects.equals(pintimekoka, that.pintimekoka)) return false;
        if (!Objects.equals(pintimeyuko, that.pintimeyuko)) return false;
        if (!Objects.equals(pintimewazaari, that.pintimewazaari))
            return false;
        if (!Objects.equals(pintimeippon, that.pintimeippon)) return false;
        if (!Objects.equals(resttime, that.resttime)) return false;
        if (!Objects.equals(gstime, that.gstime)) return false;
        if (!Objects.equals(reptime, that.reptime)) return false;
        return Objects.equals(layout, that.layout);
    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (agetext != null ? agetext.hashCode() : 0);
        result = 31 * result + (flags != null ? flags.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (weighttext != null ? weighttext.hashCode() : 0);
        result = 31 * result + (matchtime != null ? matchtime.hashCode() : 0);
        result = 31 * result + (pintimekoka != null ? pintimekoka.hashCode() : 0);
        result = 31 * result + (pintimeyuko != null ? pintimeyuko.hashCode() : 0);
        result = 31 * result + (pintimewazaari != null ? pintimewazaari.hashCode() : 0);
        result = 31 * result + (pintimeippon != null ? pintimeippon.hashCode() : 0);
        result = 31 * result + (resttime != null ? resttime.hashCode() : 0);
        result = 31 * result + (gstime != null ? gstime.hashCode() : 0);
        result = 31 * result + (reptime != null ? reptime.hashCode() : 0);
        result = 31 * result + (layout != null ? layout.hashCode() : 0);
        return result;
    }
}