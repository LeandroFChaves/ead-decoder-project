package br.com.ead.auth.repositories;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCursoRepository extends JpaRepository<UsuarioCursoModel, Long> {

    boolean existsByUsuarioAndIdCurso(UserModel userModel, Long idCurso);
}
