package br.com.ead.curso.repositories;

import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuarioModel, Long> {

    boolean existsByCursoAndIdUsuario(CursoModel cursoModel, Long idUsuario);

    @Query(value = "select * from cursos_usuarios where id_curso = :idCurso", nativeQuery = true)
    List<CursoUsuarioModel> findAllCursoUsuarioIntoCurso(@Param("idCurso") Long idCurso);

}
