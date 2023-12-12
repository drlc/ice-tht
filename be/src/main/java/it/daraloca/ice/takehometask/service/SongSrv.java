package it.daraloca.ice.takehometask.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import it.daraloca.ice.takehometask.data.album.Album;
import it.daraloca.ice.takehometask.data.artist.Artist;
import it.daraloca.ice.takehometask.data.song.ISongRepo;
import it.daraloca.ice.takehometask.data.song.Song;
import it.daraloca.ice.takehometask.data.genre.Genre;
import it.daraloca.ice.takehometask.data.user.User;
import it.daraloca.ice.takehometask.dto.SongDTO;
import it.daraloca.ice.takehometask.dto.ValidationProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@Service
public class SongSrv extends ASrv {

    @Autowired
    private ISongRepo repo;
    @PersistenceContext
    private EntityManager em;

    @Validated({ ValidationProfile.New.class })
    public UUID create(@Valid SongDTO songDTO) {
        Song entity = Song.builder().build();
        User user = em.getReference(User.class, songDTO.getUserId());
        ASrv.checkEntityExists(user);
        entity.setUser(user);
        Artist artist = em.getReference(Artist.class, songDTO.getArtistId());
        ASrv.checkEntityExists(artist);
        entity.setArtist(artist);
        if(songDTO.getAlbumId() != null) {
            Album album = em.getReference(Album.class, songDTO.getAlbumId());
            ASrv.checkEntityExists(album);
            entity.setAlbum(album);
        }
        if(songDTO.getGenreIds().size() > 0) {
            Set<Genre> genres = new HashSet<>(songDTO.getGenreIds().size());
            for (UUID genreId : songDTO.getGenreIds()) {
                genres.add(em.getReference(Genre.class, genreId));
            }
            entity.setGenres(genres);
        }
                
        entity.setName(songDTO.getName());
        entity.setYear(songDTO.getYear());
        entity.setLength(songDTO.getLength());

        Song result = repo.save(entity);
        return result.getId();
    }
    
}
