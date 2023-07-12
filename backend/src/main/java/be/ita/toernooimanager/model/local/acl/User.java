package be.ita.toernooimanager.model.local.acl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name= "ACL_USER")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Name may not be blank")
    private String name;

    @Column
    private String description;

    @Column
    @JsonIgnore
    private String password = "Admin12345!"; //=default value

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ACL_USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)}
    )
    private Set<Role> roles;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public User(String name, String description, Set<Role> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
    }
}
