package it.daraloca.ice.takehometask.endpoint;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    
}
