package br.com.ead.curso.services;

import br.com.ead.curso.models.ModuloModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ModuloService {

    List<ModuloModel> findAll();

    Page<ModuloModel> findAll(Specification<ModuloModel> spec, Pageable pageable);

    Page<ModuloModel> findAllByCurso(Specification<ModuloModel> spec, Pageable pageable);

    Optional<ModuloModel> findById(Long idModulo);

    List<ModuloModel> findAllByCurso(Long idCurso);

    Optional<ModuloModel> findModuloIntoCurso(Long idCurso, Long idModulo);

    Object save(ModuloModel moduloModel);

    void delete(ModuloModel modulo);
}
