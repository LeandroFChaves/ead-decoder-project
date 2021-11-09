package br.com.ead.curso.services.impl;

import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.models.ModuloModel;
import br.com.ead.curso.repositories.AulaRepository;
import br.com.ead.curso.repositories.ModuloRepository;
import br.com.ead.curso.services.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Override
    public List<ModuloModel> findAll() {
        return this.moduloRepository.findAll();
    }

    @Override
    public Page<ModuloModel> findAll(Specification<ModuloModel> spec, Pageable pageable) {
        return this.moduloRepository.findAll(spec, pageable);
    }

    @Override
    public Page<ModuloModel> findAllByCurso(Specification<ModuloModel> spec, Pageable pageable) {
        return this.moduloRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<ModuloModel> findById(Long idModulo) {
        return this.moduloRepository.findById(idModulo);
    }

    @Override
    public List<ModuloModel> findAllByCurso(Long idCurso) {
        return this.moduloRepository.findAllModulosIntoCurso(idCurso);
    }

    @Override
    public Optional<ModuloModel> findModuloIntoCurso(Long idCurso, Long idModulo) {
        return this.moduloRepository.findModuloIntoCurso(idCurso, idModulo);
    }

    @Override
    public Object save(ModuloModel moduloModel) {
        return this.moduloRepository.save(moduloModel);
    }

    @Override
    @Transactional
    public void delete(ModuloModel modulo) {
        List<AulaModel> listAulasIntoModulo = this.aulaRepository.findAllAulasIntoModulo(modulo.getId());

        if (!listAulasIntoModulo.isEmpty()) {
            this.aulaRepository.deleteAll(listAulasIntoModulo);
        }

        this.moduloRepository.delete(modulo);
    }

}
