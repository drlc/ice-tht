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

import it.daraloca.ice.takehometask.data.album.Album;
import it.daraloca.ice.takehometask.data.album.IAlbumRepo;
import it.daraloca.ice.takehometask.data.album.QAlbum;
import it.daraloca.ice.takehometask.dto.AlbumDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AlbumSrv {

    private static final QAlbum QA = QAlbum.album;

    @Autowired
    private IAlbumRepo repo;
    @Autowired
    private ModelMapper mapper;
    @PersistenceContext
    private EntityManager em;

    public Iterable<AlbumDTO> findAll(Pageable page, String name) {
        BooleanBuilder condition = new BooleanBuilder();
        if(name != null) {
            condition.and(QA.name.startsWithIgnoreCase(name));
        }
        Page<Album> iterab = repo.findAll(condition, page);
        List<AlbumDTO> list = new ArrayList<>();
        iterab.forEach(el -> {
            AlbumDTO dto = mapper.map(el, AlbumDTO.class);
            list.add(dto);
        });
        return new PageImpl<>(list, iterab.getPageable(), iterab.getTotalElements());
    }
    
}
