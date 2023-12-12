package it.daraloca.ice.takehometask.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.daraloca.ice.takehometask.dto.ArtistDTO;
import it.daraloca.ice.takehometask.service.ArtistSrv;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/artist")
public class ArtistCtrl {

    @Autowired
    private ArtistSrv service;
    
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<ArtistDTO> findAll(
            HttpServletRequest request,
            @PageableDefault(size = 10, page = 0) Pageable page,
            @RequestParam(name = "name", required = false) String name) {
        return service.findAll(page, name);
    }

}
