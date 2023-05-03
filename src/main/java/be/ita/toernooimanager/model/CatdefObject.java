package be.ita.toernooimanager.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "catdef", schema = "main")
public class CatdefObject {
    @EmbeddedId
    private CatdefEntity id;
}


