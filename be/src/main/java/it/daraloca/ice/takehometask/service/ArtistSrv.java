package it.daraloca.ice.takehometask.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import it.daraloca.ice.takehometask.data.artist.Artist;
import it.daraloca.ice.takehometask.data.artist.IArtistRepo;
import it.daraloca.ice.takehometask.data.artist.QArtist;
import it.daraloca.ice.takehometask.dto.ArtistDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ArtistSrv {

    private static final QArtist QA = QArtist.artist;

    @Autowired
    private IArtistRepo repo;
    @Autowired
    private ModelMapper mapper;
    @PersistenceContext
    private EntityManager em;

    public Iterable<ArtistDTO> findAll(Pageable page, String name) {
        BooleanBuilder condition = new BooleanBuilder();
        if(name != null) {
            condition.and(QA.name.startsWithIgnoreCase(name));
        }
        Page<Artist> iterab = repo.findAll(condition, page);
        List<ArtistDTO> list = new ArrayList<>();
        iterab.forEach(el -> {
            ArtistDTO dto = mapper.map(el, ArtistDTO.class);
            list.add(dto);
        });
        return new PageImpl<>(list, iterab.getPageable(), iterab.getTotalElements());
    }
    
}
