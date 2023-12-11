package it.daraloca.ice.takehometask.data.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepo extends JpaRepository<User, UUID>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

    @Override
    default void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(root.name).first((path, value) -> path.containsIgnoreCase(value));
    }
}
