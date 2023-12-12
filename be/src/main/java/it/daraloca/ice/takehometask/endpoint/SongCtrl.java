package it.daraloca.ice.takehometask.endpoint;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.daraloca.ice.takehometask.dto.DetailedSongDTO;
import it.daraloca.ice.takehometask.dto.SongDTO;
import it.daraloca.ice.takehometask.service.SongSrv;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/song")
public class SongCtrl {

    @Autowired
    private SongSrv service;

    /**
     * This API creates a new song
     * 
     * @param request the http request
     * @param data    the song to create
     * @return
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public UUID create(HttpServletRequest request, @RequestBody SongDTO data) {
        return service.create(data);
    }

    /**
     * This API return a page of the elements requested.
     * 
     * @param request         the http request
     * @param page            the pageable, with page info and sorting
     * @param userId          the user id
     * @return the page of requested elements
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<SongDTO> findAll(
            HttpServletRequest request,
            @PageableDefault(size = 10, page = 0) Pageable page,
            @RequestParam(name = "user-id", required = true) UUID userId,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "artist-name", required = false) String artistName) {
        return service.findAll(page, userId, year, artistName);
    }
    
    /**
     * This API return the song requested by its id.
     * 
     * @param request     the http request
     * @param songId      the id of the song
     * @return the requested song
     */
    @GetMapping("/{songId}")
    @ResponseStatus(code = HttpStatus.OK)
    public DetailedSongDTO read(
            HttpServletRequest request,
            @PathVariable(name = "songId") UUID songId
        ) {
        return service.read(songId);
    }
}
