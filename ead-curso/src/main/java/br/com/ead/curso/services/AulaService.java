package br.com.ead.curso.services;

import br.com.ead.curso.models.AulaModel;

import java.util.List;
import java.util.Optional;

public interface AulaService {

    List<AulaModel> findAll();

    List<AulaModel> findAllByModulo(Long idModulo);

    Optional<AulaModel> findAulaIntoModulo(Long idModulo, Long idAula);

    Object save(AulaModel aulaModel);

    void delete(AulaModel aulaModel);

}
