package br.com.ead.curso.services;

import br.com.ead.curso.models.ModuloModel;

import java.util.List;
import java.util.Optional;

public interface ModuloService {

    List<ModuloModel> findAll();

    Optional<ModuloModel> findById(Long idModulo);

    List<ModuloModel> findAllByCurso(Long idCurso);

    Optional<ModuloModel> findModuloIntoCurso(Long idCurso, Long idModulo);

    Object save(ModuloModel moduloModel);

    void delete(ModuloModel modulo);
}
