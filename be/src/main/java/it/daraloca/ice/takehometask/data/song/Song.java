package it.daraloca.ice.takehometask.data.song;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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

    @ToString.Include
    @NotNull
    private String name;
    @ToString.Include
    private Integer year;
    @ToString.Include
    private Integer length;

    @ManyToOne
    @NotNull
    private User user;
    @ManyToOne
    @NotNull
    private Artist artist;
    @ManyToOne
    private Album album;
    @ManyToMany
    @Builder.Default
    private Set<Genre> genres = new HashSet<>(0);

}