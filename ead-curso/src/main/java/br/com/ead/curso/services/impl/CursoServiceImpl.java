package br.com.ead.curso.services.impl;

import br.com.ead.curso.dtos.NotificacaoCommandDTO;
import br.com.ead.curso.models.AulaModel;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.ModuloModel;
import br.com.ead.curso.models.UsuarioModel;
import br.com.ead.curso.publishers.NotificacaoCommandPublisher;
import br.com.ead.curso.repositories.AulaRepository;
import br.com.ead.curso.repositories.CursoRepository;
import br.com.ead.curso.repositories.ModuloRepository;
import br.com.ead.curso.services.CursoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    NotificacaoCommandPublisher notificacaoCommandPublisher;

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
    public boolean existsByCursoAndUsuario(Long idCurso, Long idUsuario) {
        return this.cursoRepository.existsByCursoAndUsuario(idCurso, idUsuario);
    }

    @Override
    public CursoModel save(CursoModel curso) {
        return this.cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void saveMatriculaUsuarioInCurso(Long id, Long idUsuario) {
        this.cursoRepository.saveCursoUsuario(id, idUsuario);
    }

    @Override
    @Transactional
    public void saveMatriculaUsuarioInCursoAndEnviaNotificacao(CursoModel cursoModel, UsuarioModel usuarioModel) {
        this.cursoRepository.saveCursoUsuario(cursoModel.getIdCurso(), usuarioModel.getIdUsuario());

        try {
            NotificacaoCommandDTO notificacaoCommandDTO = new NotificacaoCommandDTO();

            notificacaoCommandDTO.setTitulo("Bem Vindo(a) ao Curso: " + cursoModel.getNome());
            notificacaoCommandDTO.setMensagem(usuarioModel.getNomeCompleto() + " a sua inscrição foi realizada com sucesso!");
            notificacaoCommandDTO.setIdUsuario(usuarioModel.getIdUsuario());

            this.notificacaoCommandPublisher.publishNotificacaoCommand(notificacaoCommandDTO);
        } catch (Exception e) {
            log.warn("Problemas no envio da notificação para o usuário!");
        }
    }

    @Override
    @Transactional
    public void delete(CursoModel curso) {
        List<ModuloModel> listModulosIntoCurso = this.moduloRepository.findAllModulosIntoCurso(curso.getIdCurso());

        if (!listModulosIntoCurso.isEmpty()) {
            for (ModuloModel modulo : listModulosIntoCurso) {
                List<AulaModel> listAulasIntoModulo = this.aulaRepository.findAllAulasIntoModulo(modulo.getIdModulo());

                if (!listAulasIntoModulo.isEmpty()) {
                    this.aulaRepository.deleteAll(listAulasIntoModulo);
                }
            }

            this.moduloRepository.deleteAll(listModulosIntoCurso);
        }

        this.cursoRepository.deleteCursoUsuarioByCurso(curso.getIdCurso());
        this.cursoRepository.delete(curso);
    }

}
