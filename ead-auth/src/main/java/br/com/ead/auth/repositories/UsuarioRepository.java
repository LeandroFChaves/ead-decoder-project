package br.com.ead.auth.repositories;

import br.com.ead.auth.models.UsuarioModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>, JpaSpecificationExecutor<UsuarioModel> {
    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<UsuarioModel> findByUsuario(String usuario);

    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<UsuarioModel> findById(Long idUsuario);
}
