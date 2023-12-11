package it.daraloca.ice.takehometask.data.genre;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

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
import lombok.NonNull;
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
public class Genre  extends AEntity {
    private static final long serialVersionUID = 74398750347934L;

    @Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(length = 16)
    @ToString.Include
    @EqualsAndHashCode.Include
    private UUID id;

    @ToString.Include
    @Nonnull
    @NonNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Song> songs = new HashSet<>(0);
}