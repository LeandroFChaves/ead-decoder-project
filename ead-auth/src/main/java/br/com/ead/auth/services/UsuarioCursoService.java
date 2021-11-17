package br.com.ead.auth.services;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;

public interface UsuarioCursoService {
    boolean existsByUsuarioAndCurso(UserModel userModel, Long idCurso);

    boolean existsByIdCurso(Long idCurso);

    UsuarioCursoModel save(UsuarioCursoModel usuarioCursoModel);

    void deleteUsuarioCursoByCurso(Long idCurso);
}
