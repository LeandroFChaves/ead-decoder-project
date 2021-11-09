package br.com.ead.curso.repositories;

import br.com.ead.curso.models.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long>, JpaSpecificationExecutor<CursoModel> {
}
