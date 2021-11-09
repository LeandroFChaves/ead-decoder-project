package br.com.ead.curso.services.impl;

import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.repositories.AulaRepository;
import br.com.ead.curso.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaServiceImpl implements AulaService {

    @Autowired
    AulaRepository aulaRepository;

    @Override
    public List<AulaModel> findAll() {
        return this.aulaRepository.findAll();
    }

    @Override
    public Page<AulaModel> findAll(Specification<AulaModel> spec, Pageable pageable) {
        return this.aulaRepository.findAll(spec, pageable);
    }

    @Override
    public Page<AulaModel> findAllByModulo(Specification<AulaModel> spec, Pageable pageable) {
        return this.aulaRepository.findAll(spec, pageable);
    }

    @Override
    public List<AulaModel> findAllByModulo(Long idModulo) {
        return this.aulaRepository.findAllAulasIntoModulo(idModulo);
    }

    @Override
    public Optional<AulaModel> findAulaIntoModulo(Long idModulo, Long idAula) {
        return this.aulaRepository.findAulaIntoModulo(idModulo, idAula);
    }

    @Override
    public Object save(AulaModel aulaModel) {
        return this.aulaRepository.save(aulaModel);
    }

    @Override
    public void delete(AulaModel aulaModel) {
        this.aulaRepository.delete(aulaModel);
    }
}
