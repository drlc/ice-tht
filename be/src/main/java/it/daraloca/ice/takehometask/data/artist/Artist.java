package it.daraloca.ice.takehometask.data.artist;
import java.util.Set;
import java.util.UUID;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

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
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
// JPA
@Entity
@DynamicInsert
@DynamicUpdate
public class Artist extends AEntity {
    private static final long serialVersionUID = 587938574389573942L;

    @Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(length = 16)
    @ToString.Include
    @EqualsAndHashCode.Include
    private UUID id;

    @ToString.Include
    @NotNull
    private String name;

    @OneToMany(mappedBy = "artist")
    @Builder.Default
    private final Set<Song> songs = new HashSet<>(0);
}