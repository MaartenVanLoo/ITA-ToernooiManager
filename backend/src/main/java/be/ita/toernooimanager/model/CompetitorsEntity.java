package be.ita.toernooimanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "competitors", schema = "main")
public class CompetitorsEntity {
    @Basic
    @Id
    @Column(name = "index")
    private Integer index;
    @Basic
    @Column(name = "last")
    private String last;
    @Basic
    @Column(name = "first")
    private String first;
    @Basic
    @Column(name = "birthyear")
    private Integer birthyear;
    @Basic
    @Column(name = "belt")
    private Integer belt;
    @Basic
    @Column(name = "club")
    private String club;
    @Basic
    @Column(name = "regcategory")
    private String regcategory;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "visible")
    private Integer visible;
    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "deleted")
    private Integer deleted;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "seeding")
    private Integer seeding;
    @Basic
    @Column(name = "clubseeding")
    private Integer clubseeding;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "coachid")
    private String coachid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompetitorsEntity that = (CompetitorsEntity) o;

        if (!Objects.equals(index, that.index)) return false;
        if (!Objects.equals(last, that.last)) return false;
        if (!Objects.equals(first, that.first)) return false;
        if (!Objects.equals(birthyear, that.birthyear)) return false;
        if (!Objects.equals(belt, that.belt)) return false;
        if (!Objects.equals(club, that.club)) return false;
        if (!Objects.equals(regcategory, that.regcategory)) return false;
        if (!Objects.equals(weight, that.weight)) return false;
        if (!Objects.equals(visible, that.visible)) return false;
        if (!Objects.equals(category, that.category)) return false;
        if (!Objects.equals(deleted, that.deleted)) return false;
        if (!Objects.equals(country, that.country)) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(seeding, that.seeding)) return false;
        if (!Objects.equals(clubseeding, that.clubseeding)) return false;
        if (!Objects.equals(comment, that.comment)) return false;
        return Objects.equals(coachid, that.coachid);
    }

    @Override
    public int hashCode() {
        int result = index != null ? index.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (birthyear != null ? birthyear.hashCode() : 0);
        result = 31 * result + (belt != null ? belt.hashCode() : 0);
        result = 31 * result + (club != null ? club.hashCode() : 0);
        result = 31 * result + (regcategory != null ? regcategory.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (visible != null ? visible.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (seeding != null ? seeding.hashCode() : 0);
        result = 31 * result + (clubseeding != null ? clubseeding.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (coachid != null ? coachid.hashCode() : 0);
        return result;
    }
}
