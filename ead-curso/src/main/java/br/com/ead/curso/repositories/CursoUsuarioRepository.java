package br.com.ead.curso.repositories;

import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuarioModel, Long> {

    boolean existsByCursoAndIdUsuario(CursoModel cursoModel, Long idUsuario);

}
