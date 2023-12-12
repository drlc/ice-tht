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

import it.daraloca.ice.takehometask.data.genre.Genre;
import it.daraloca.ice.takehometask.data.genre.IGenreRepo;
import it.daraloca.ice.takehometask.data.genre.QGenre;
import it.daraloca.ice.takehometask.dto.GenreDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class GenreSrv {

    private static final QGenre QG = QGenre.genre;

    @Autowired
    private IGenreRepo repo;
    @Autowired
    private ModelMapper mapper;
    @PersistenceContext
    private EntityManager em;

    public Iterable<GenreDTO> findAll(Pageable page, String genreName) {
        BooleanBuilder condition = new BooleanBuilder();
        if(genreName != null) {
            condition.and(QG.name.startsWithIgnoreCase(genreName));
        }
        Page<Genre> iterab = repo.findAll(condition, page);
        List<GenreDTO> list = new ArrayList<>();
        iterab.forEach(el -> {
            GenreDTO dto = mapper.map(el, GenreDTO.class);
            list.add(dto);
        });
        return new PageImpl<>(list, iterab.getPageable(), iterab.getTotalElements());
    }
    
}
