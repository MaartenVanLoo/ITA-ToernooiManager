package be.ita.toernooimanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "matches", schema = "main")
public class MatchesEntity {
    @Basic
    @Column(name = "category")
    private Integer category;

    @Id
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "blue")
    private Integer blue;
    @Basic
    @Column(name = "white")
    private Integer white;
    @Basic
    @Column(name = "blue_score")
    private Integer blueScore;
    @Basic
    @Column(name = "white_score")
    private Integer whiteScore;
    @Basic
    @Column(name = "blue_points")
    private Integer bluePoints;
    @Basic
    @Column(name = "white_points")
    private Integer whitePoints;
    @Basic
    @Column(name = "time")
    private Integer time;
    @Basic
    @Column(name = "comment")
    private Integer comment;
    @Basic
    @Column(name = "deleted")
    private Integer deleted;
    @Basic
    @Column(name = "forcedtatami")
    private Integer forcedtatami;
    @Basic
    @Column(name = "forcednumber")
    private Integer forcednumber;
    @Basic
    @Column(name = "date")
    private Integer date;
    @Basic
    @Column(name = "legend")
    private Integer legend;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchesEntity that = (MatchesEntity) o;

        if (!Objects.equals(category, that.category)) return false;
        if (!Objects.equals(number, that.number)) return false;
        if (!Objects.equals(blue, that.blue)) return false;
        if (!Objects.equals(white, that.white)) return false;
        if (!Objects.equals(blueScore, that.blueScore)) return false;
        if (!Objects.equals(whiteScore, that.whiteScore)) return false;
        if (!Objects.equals(bluePoints, that.bluePoints)) return false;
        if (!Objects.equals(whitePoints, that.whitePoints)) return false;
        if (!Objects.equals(time, that.time)) return false;
        if (!Objects.equals(comment, that.comment)) return false;
        if (!Objects.equals(deleted, that.deleted)) return false;
        if (!Objects.equals(forcedtatami, that.forcedtatami)) return false;
        if (!Objects.equals(forcednumber, that.forcednumber)) return false;
        if (!Objects.equals(date, that.date)) return false;
        return Objects.equals(legend, that.legend);
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (blue != null ? blue.hashCode() : 0);
        result = 31 * result + (white != null ? white.hashCode() : 0);
        result = 31 * result + (blueScore != null ? blueScore.hashCode() : 0);
        result = 31 * result + (whiteScore != null ? whiteScore.hashCode() : 0);
        result = 31 * result + (bluePoints != null ? bluePoints.hashCode() : 0);
        result = 31 * result + (whitePoints != null ? whitePoints.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (forcedtatami != null ? forcedtatami.hashCode() : 0);
        result = 31 * result + (forcednumber != null ? forcednumber.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (legend != null ? legend.hashCode() : 0);
        return result;
    }
}
