package br.com.ead.auth.repositories;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioCursoRepository extends JpaRepository<UsuarioCursoModel, Long> {

    boolean existsByUsuarioAndIdCurso(UserModel userModel, Long idCurso);

    @Query(value = "select * from usuarios_cursos where id_usuario = :idUsuario", nativeQuery = true)
    List<UsuarioCursoModel> findAllUserCourseIntoUser(@Param("idUsuario") Long idUsuario);

    boolean existsByIdCurso(Long idCurso);

    void deleteAllByIdCurso(Long idCurso);
}
