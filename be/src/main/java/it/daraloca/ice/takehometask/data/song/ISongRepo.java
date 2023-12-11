package it.daraloca.ice.takehometask.data.song;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.stereotype.Repository;


@Repository
public interface ISongRepo extends JpaRepository<Song, UUID>, QuerydslPredicateExecutor<Song>, QuerydslBinderCustomizer<QSong> {

    @Override
    default void customize(QuerydslBindings bindings, QSong root) {
        bindings.bind(root.name).first((path, value) -> path.containsIgnoreCase(value));
    }
    
}
