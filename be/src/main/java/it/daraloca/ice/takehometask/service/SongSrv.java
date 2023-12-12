package it.daraloca.ice.takehometask.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.querydsl.core.BooleanBuilder;

import it.daraloca.ice.takehometask.data.album.Album;
import it.daraloca.ice.takehometask.data.artist.Artist;
import it.daraloca.ice.takehometask.data.song.ISongRepo;
import it.daraloca.ice.takehometask.data.song.QSong;
import it.daraloca.ice.takehometask.data.song.Song;
import it.daraloca.ice.takehometask.data.genre.Genre;
import it.daraloca.ice.takehometask.data.user.User;
import it.daraloca.ice.takehometask.dto.DetailedSongDTO;
import it.daraloca.ice.takehometask.dto.SongDTO;
import it.daraloca.ice.takehometask.dto.ValidationProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@Service
public class SongSrv extends ASrv {

    private static final QSong QS = QSong.song;

    @Autowired
    private ISongRepo repo;
    @Autowired
    private ModelMapper mapper;
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

    public Iterable<SongDTO> findAll(Pageable page, UUID userId, Integer year, String artistName) {
        BooleanBuilder condition = new BooleanBuilder(QS.user.id.eq(userId));
        if(year != null) {
            condition.and(QS.year.eq(year));
        }
        if(artistName != null) {
            condition.and(QS.artist.name.eq(artistName));
        }
        Page<Song> iterab = repo.findAll(condition, page);
        List<SongDTO> list = new ArrayList<>();
        iterab.forEach(el -> {
            SongDTO dto = mapper.map(el, SongDTO.class);
            for(Genre genre : el.getGenres()) {
                dto.getGenreIds().add(genre.getId());
            }
            list.add(dto);
        });
        return new PageImpl<>(list, iterab.getPageable(), iterab.getTotalElements());
    }

    public DetailedSongDTO read(UUID songId) {
        Song entity = repo.findById(songId).orElseThrow(() -> new IllegalArgumentException("Song not found"));
        DetailedSongDTO result = mapper.map(entity, DetailedSongDTO.class);
        for(Genre genre : entity.getGenres()) {
            result.getGenreIds().add(genre.getId());
            result.getGenreNames().add(genre.getName());
        }
        return result;
    }
    
}
