package br.com.ead.auth.repositories;

import br.com.ead.auth.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>, JpaSpecificationExecutor<UsuarioModel> {
    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);
}
