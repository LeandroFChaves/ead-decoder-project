package br.com.ead.curso.repositories;

import br.com.ead.curso.models.ModuloModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<ModuloModel, Long> {

    @EntityGraph(attributePaths = {"curso"})
    ModuloModel findByTitulo(String titulo);

    @Query(value = "select * from modulos where id_curso = :idCurso", nativeQuery = true)
    List<ModuloModel> findAllModulosIntoCurso(@Param("idCurso") Long idCurso);

    @Query(value = "select * from modulos where id_curso = :idCurso and id = :idModulo", nativeQuery = true)
    Optional<ModuloModel> findModuloIntoCurso(@Param("idCurso") Long idCurso, @Param("idModulo") Long idModulo);
}
