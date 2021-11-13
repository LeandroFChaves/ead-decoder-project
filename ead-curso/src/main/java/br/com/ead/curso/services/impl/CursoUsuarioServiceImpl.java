package br.com.ead.curso.services.impl;

import br.com.ead.curso.clients.UsuarioClient;
import br.com.ead.curso.models.CursoModel;
import br.com.ead.curso.models.CursoUsuarioModel;
import br.com.ead.curso.repositories.CursoUsuarioRepository;
import br.com.ead.curso.services.CursoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoUsuarioServiceImpl implements CursoUsuarioService {

    @Autowired
    CursoUsuarioRepository cursoUsuarioRepository;

    @Autowired
    UsuarioClient usuarioClient;

    @Override
    public boolean existsByCursoAndUsuario(CursoModel cursoModel, Long idUsuario) {
        return this.cursoUsuarioRepository.existsByCursoAndIdUsuario(cursoModel, idUsuario);
    }

    @Override
    public CursoUsuarioModel save(CursoUsuarioModel cursoUsuarioModel) {
        return this.cursoUsuarioRepository.save(cursoUsuarioModel);
    }

    @Override
    @Transactional
    public CursoUsuarioModel saveAndMatriculaUsuarioInCurso(CursoUsuarioModel cursoUsuarioModel) {
        cursoUsuarioModel = this.cursoUsuarioRepository.save(cursoUsuarioModel);
        this.usuarioClient.postMatricularUsuarioInCurso(cursoUsuarioModel.getCurso().getId(), cursoUsuarioModel.getIdUsuario());

        return cursoUsuarioModel;
    }
}
