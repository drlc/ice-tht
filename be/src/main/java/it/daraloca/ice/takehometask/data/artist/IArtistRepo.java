package it.daraloca.ice.takehometask.data.artist;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.stereotype.Repository;


@Repository
public interface IArtistRepo extends JpaRepository<Artist, UUID>, QuerydslPredicateExecutor<Artist>, QuerydslBinderCustomizer<QArtist> {

    @Override
    default void customize(QuerydslBindings bindings, QArtist root) {
        bindings.bind(root.name).first((path, value) -> path.containsIgnoreCase(value));
    }
    
}
