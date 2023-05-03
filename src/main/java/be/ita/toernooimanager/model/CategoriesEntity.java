package be.ita.toernooimanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "categories", schema = "main")
public class CategoriesEntity {
    @Id
    @Basic
    @Column(name = "index")
    private Integer index;
    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "tatami")
    private Integer tatami;
    @Basic
    @Column(name = "deleted")
    private Integer deleted;
    @Basic
    @Column(name = "group")
    private Integer group;
    @Basic
    @Column(name = "system")
    private Integer system;
    @Basic
    @Column(name = "numcomp")
    private Integer numcomp;
    @Basic
    @Column(name = "table")
    private Integer table;
    @Basic
    @Column(name = "wishsys")
    private Integer wishsys;
    @Basic
    @Column(name = "pos1")
    private Integer pos1;
    @Basic
    @Column(name = "pos2")
    private Integer pos2;
    @Basic
    @Column(name = "pos3")
    private Integer pos3;
    @Basic
    @Column(name = "pos4")
    private Integer pos4;
    @Basic
    @Column(name = "pos5")
    private Integer pos5;
    @Basic
    @Column(name = "pos6")
    private Integer pos6;
    @Basic
    @Column(name = "pos7")
    private Integer pos7;
    @Basic
    @Column(name = "pos8")
    private Integer pos8;
    @Basic
    @Column(name = "color")
    private String color;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesEntity that = (CategoriesEntity) o;

        if (!Objects.equals(index, that.index)) return false;
        if (!Objects.equals(category, that.category)) return false;
        if (!Objects.equals(tatami, that.tatami)) return false;
        if (!Objects.equals(deleted, that.deleted)) return false;
        if (!Objects.equals(group, that.group)) return false;
        if (!Objects.equals(system, that.system)) return false;
        if (!Objects.equals(numcomp, that.numcomp)) return false;
        if (!Objects.equals(table, that.table)) return false;
        if (!Objects.equals(wishsys, that.wishsys)) return false;
        if (!Objects.equals(pos1, that.pos1)) return false;
        if (!Objects.equals(pos2, that.pos2)) return false;
        if (!Objects.equals(pos3, that.pos3)) return false;
        if (!Objects.equals(pos4, that.pos4)) return false;
        if (!Objects.equals(pos5, that.pos5)) return false;
        if (!Objects.equals(pos6, that.pos6)) return false;
        if (!Objects.equals(pos7, that.pos7)) return false;
        if (!Objects.equals(pos8, that.pos8)) return false;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        int result = index != null ? index.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (tatami != null ? tatami.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (system != null ? system.hashCode() : 0);
        result = 31 * result + (numcomp != null ? numcomp.hashCode() : 0);
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (wishsys != null ? wishsys.hashCode() : 0);
        result = 31 * result + (pos1 != null ? pos1.hashCode() : 0);
        result = 31 * result + (pos2 != null ? pos2.hashCode() : 0);
        result = 31 * result + (pos3 != null ? pos3.hashCode() : 0);
        result = 31 * result + (pos4 != null ? pos4.hashCode() : 0);
        result = 31 * result + (pos5 != null ? pos5.hashCode() : 0);
        result = 31 * result + (pos6 != null ? pos6.hashCode() : 0);
        result = 31 * result + (pos7 != null ? pos7.hashCode() : 0);
        result = 31 * result + (pos8 != null ? pos8.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
