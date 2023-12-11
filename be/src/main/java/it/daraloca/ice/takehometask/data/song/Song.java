package it.daraloca.ice.takehometask.data.song;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import it.daraloca.ice.takehometask.data.AEntity;
import it.daraloca.ice.takehometask.data.album.Album;
import it.daraloca.ice.takehometask.data.artist.Artist;
import it.daraloca.ice.takehometask.data.genre.Genre;
import it.daraloca.ice.takehometask.data.user.User;
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
public class Song extends AEntity {
    private static final long serialVersionUID = 589348954389L;

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
    @ToString.Include
    private Integer year;
    @ToString.Include
    private Integer length;

    @ManyToOne
    @Nonnull
    @NonNull
    private User user;
    @ManyToOne
    @Nonnull
    @NonNull
    private Artist artist;
    @ManyToOne
    @Nullable
    private Album album;
    @ManyToMany
    @Builder.Default
    private Set<Genre> genres = new HashSet<>(0);

}