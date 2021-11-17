package br.com.ead.curso.services;

import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;

public interface CursoUsuarioService {
    boolean existsByCursoAndUsuario(CursoModel cursoModel, Long idUsuario);

    boolean existsByIdUsuario(Long idUsuario);

    CursoUsuarioModel save(CursoUsuarioModel cursoUsuarioModel);

    CursoUsuarioModel saveAndMatriculaUsuarioInCurso(CursoUsuarioModel cursoUsuarioModel);

    void deleteCursoUsuarioByUsuario(Long idUsuario);
}
