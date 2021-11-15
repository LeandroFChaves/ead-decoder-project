package br.com.ead.curso.services.impl;

import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;
import br.com.ead.curso.models.ModuloModel;
import br.com.ead.curso.repositories.AulaRepository;
import br.com.ead.curso.repositories.CursoRepository;
import br.com.ead.curso.repositories.CursoUsuarioRepository;
import br.com.ead.curso.repositories.ModuloRepository;
import br.com.ead.curso.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    CursoUsuarioRepository cursoUsuarioRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Override
    public List<CursoModel> findAll() {
        return this.cursoRepository.findAll();
    }

    @Override
    public Page<CursoModel> findAll(Specification<CursoModel> spec, Pageable pageable) {
        return this.cursoRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<CursoModel> findById(Long idCurso) {
        return this.cursoRepository.findById(idCurso);
    }

    @Override
    public CursoModel save(CursoModel curso) {
        return this.cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void delete(CursoModel curso) {
        List<ModuloModel> listModulosIntoCurso = this.moduloRepository.findAllModulosIntoCurso(curso.getId());

        if (!listModulosIntoCurso.isEmpty()) {
            for (ModuloModel modulo : listModulosIntoCurso) {
                List<AulaModel> listAulasIntoModulo = this.aulaRepository.findAllAulasIntoModulo(modulo.getId());

                if (!listAulasIntoModulo.isEmpty()) {
                    this.aulaRepository.deleteAll(listAulasIntoModulo);
                }
            }

            this.moduloRepository.deleteAll(listModulosIntoCurso);
        }

        List<CursoUsuarioModel> cursoUsuarioModelList = this.cursoUsuarioRepository.findAllCursoUsuarioIntoCurso(curso.getId());
        if (!cursoUsuarioModelList.isEmpty()) {
            this.cursoUsuarioRepository.deleteAll(cursoUsuarioModelList);
        }

        this.cursoRepository.delete(curso);
    }

}
