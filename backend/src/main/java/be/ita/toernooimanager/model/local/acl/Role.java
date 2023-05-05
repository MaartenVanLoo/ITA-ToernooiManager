package be.ita.toernooimanager.model.local.acl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_PRIV",
            joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PRIV_ID", referencedColumnName = "id", nullable = false)})
    private Set<Privilege> privileges;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updateDateTime;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String description, Set<Privilege> privileges) {
        this.name = name;
        this.description = description;
        this.privileges = privileges;
    }
}
