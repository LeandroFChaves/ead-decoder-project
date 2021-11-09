package br.com.ead.curso.services;

import br.com.ead.curso.models.AulaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AulaService {

    List<AulaModel> findAll();

    Page<AulaModel> findAll(Specification<AulaModel> spec, Pageable pageable);

    Page<AulaModel> findAllByModulo(Specification<AulaModel> spec, Pageable pageable);

    List<AulaModel> findAllByModulo(Long idModulo);

    Optional<AulaModel> findAulaIntoModulo(Long idModulo, Long idAula);

    Object save(AulaModel aulaModel);

    void delete(AulaModel aulaModel);
}
