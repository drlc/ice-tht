package it.daraloca.ice.takehometask.data.genre;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.stereotype.Repository;


@Repository
public interface IGenreRepo extends JpaRepository<Genre, UUID>, QuerydslPredicateExecutor<Genre>, QuerydslBinderCustomizer<QGenre> {

    @Override
    default void customize(QuerydslBindings bindings, QGenre root) {
        bindings.bind(root.name).first((path, value) -> path.containsIgnoreCase(value));
    }
    
}
