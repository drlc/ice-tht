package it.daraloca.ice.takehometask.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.daraloca.ice.takehometask.data.album.Album;
import it.daraloca.ice.takehometask.data.album.IAlbumRepo;
import it.daraloca.ice.takehometask.data.artist.Artist;
import it.daraloca.ice.takehometask.data.artist.IArtistRepo;
import it.daraloca.ice.takehometask.data.genre.Genre;
import it.daraloca.ice.takehometask.data.genre.IGenreRepo;
import it.daraloca.ice.takehometask.data.user.IUserRepo;
import it.daraloca.ice.takehometask.data.user.User;
import jakarta.annotation.PostConstruct;

@Component
public class InitDBSrv {

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IArtistRepo artistRepo;
    @Autowired
    private IAlbumRepo albumRepo;
    @Autowired
    private IGenreRepo genreRepo;


    @PostConstruct
    public void init(){
        if(userRepo.count() > 0) {
            System.out.println("InitDBSrv.init() - DB already initialized");
            userRepo.findAll().forEach(System.out::println);
            return;
        }
        User user = User.builder().name("user-one").build();
        userRepo.save(user);
        Album album = Album.builder().name("album-one").build();
        albumRepo.save(album);
        Artist artistOne = Artist.builder().name("artist-one").build();
        Artist artistTwo = Artist.builder().name("artist-two").build();
        artistRepo.save(artistOne);
        artistRepo.save(artistTwo);    
        Genre genreOne = Genre.builder().name("genre-one").build();
        Genre genreTwo = Genre.builder().name("genre-two").build();
        genreRepo.save(genreOne);
        genreRepo.save(genreTwo);
        System.out.println("InitDBSrv.init() - user: " + user.toString());
        System.out.println("InitDBSrv.init() - album: " + album.toString());
        System.out.println("InitDBSrv.init() - artistOne: " + artistOne.toString());
        System.out.println("InitDBSrv.init() - artistTwo: " + artistTwo.toString());
        System.out.println("InitDBSrv.init() - genreOne: " + genreOne.toString());
        System.out.println("InitDBSrv.init() - genreTwo: " + genreTwo.toString());
        
    }
    
}
