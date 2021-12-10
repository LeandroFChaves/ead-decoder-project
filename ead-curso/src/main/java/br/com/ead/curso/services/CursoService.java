package br.com.ead.curso.services;

import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<CursoModel> findAll();

    Page<CursoModel> findAll(Specification<CursoModel> spec, Pageable pageable);

    Optional<CursoModel> findById(Long idCurso);

    boolean existsByCursoAndUsuario(Long idCurso, Long idUsuario);

    CursoModel save(CursoModel curso);

    void saveMatriculaUsuarioInCurso(Long id, Long idUsuario);

    void saveMatriculaUsuarioInCursoAndEnviaNotificacao(CursoModel cursoModel, UsuarioModel usuarioModel);

    void delete(CursoModel curso);
}
