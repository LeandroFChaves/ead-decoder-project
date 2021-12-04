package br.com.ead.curso.repositories;

import br.com.ead.curso.models.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long>, JpaSpecificationExecutor<CursoModel> {

    @Query(value = "SELECT CASE WHEN COUNT(cursosUsuarios) > 0 THEN true ELSE false END " +
            "  FROM CURSOS_USUARIOS cursosUsuarios" +
            " WHERE cursosUsuarios.id_curso= :idCurso" +
            "   AND cursosUsuarios.id_usuario= :idUsuario", nativeQuery = true)
    boolean existsByCursoAndUsuario(@Param("idCurso") Long idCurso, @Param("idUsuario") Long idUsuario);

    @Modifying
    @Query(value = "INSERT INTO CURSOS_USUARIOS VALUES (:idCurso, :idUsuario);", nativeQuery = true)
    void saveCursoUsuario(@Param("idCurso") Long idCurso, @Param("idUsuario") Long idUsuario);

}
