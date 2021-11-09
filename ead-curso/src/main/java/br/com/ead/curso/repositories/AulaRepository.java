package br.com.ead.curso.repositories;

import br.com.ead.curso.models.AulaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<AulaModel, Long>, JpaSpecificationExecutor<AulaModel> {

    @Query(value = "select * from aulas where id_modulo = :idModulo", nativeQuery = true)
    List<AulaModel> findAllAulasIntoModulo(@Param("idModulo") Long idModulo);

    @Query(value = "select * from aulas where id_modulo = :idModulo and id = :idAula", nativeQuery = true)
    Optional<AulaModel> findAulaIntoModulo(Long idModulo, Long idAula);
}
