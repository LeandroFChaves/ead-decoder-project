package br.com.ead.curso.services;

import br.com.ead.curso.models.CursoModel;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<CursoModel> findAll();

    Optional<CursoModel> findById(Long idCurso);

    CursoModel save(CursoModel curso);

    void delete(CursoModel curso);
}
