package it.daraloca.ice.takehometask.data.album;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.stereotype.Repository;


@Repository
public interface IAlbumRepo extends JpaRepository<Album, UUID>, QuerydslPredicateExecutor<Album>, QuerydslBinderCustomizer<QAlbum> {

    @Override
    default void customize(QuerydslBindings bindings, QAlbum root) {
        bindings.bind(root.name).first((path, value) -> path.containsIgnoreCase(value));
    }
    
}
