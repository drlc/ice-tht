package it.daraloca.ice.takehometask.data.user;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.daraloca.ice.takehometask.data.AEntity;
import it.daraloca.ice.takehometask.data.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
// JPA
@Entity
@DynamicInsert
@DynamicUpdate
public class User extends AEntity {
    private static final long serialVersionUID = 439988004794424302L;

    @ToString.Include
    @NotNull
    private String name;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private final Set<Song> songs = new HashSet<>(0);
}
