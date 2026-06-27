package br.com.sttalis.connect.domain.repository;

import br.com.sttalis.connect.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Extender JpaRepository frornece CRUD completo
 */

public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * DERIVED QUERY METHODS:
     * O spring interpreta o nome do metodo em ingles e gera o sql dinamicamente
     * SQL gerado: SELEC CASE WHEN COUNT (u) > 0 THEN true ELSE false END FRON td_users u WHERE u.email = ?
     */

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
