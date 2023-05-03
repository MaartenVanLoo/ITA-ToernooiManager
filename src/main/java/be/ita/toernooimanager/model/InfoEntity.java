package be.ita.toernooimanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "info", schema = "main")
public class InfoEntity {
    @Id
    @Basic
    @Column(name = "item")
    private String item;
    @Basic
    @Column(name = "value")
    private String value;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoEntity that = (InfoEntity) o;

        if (!Objects.equals(item, that.item)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
