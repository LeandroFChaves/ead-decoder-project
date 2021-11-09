package br.com.ead.curso.services;

import br.com.ead.curso.models.CursoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<CursoModel> findAll();

    Page<CursoModel> findAll(Specification<CursoModel> spec, Pageable pageable);

    Optional<CursoModel> findById(Long idCurso);

    CursoModel save(CursoModel curso);

    void delete(CursoModel curso);
}
