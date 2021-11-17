package br.com.ead.auth.services.impl;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.models.UsuarioCursoModel;
import br.com.ead.auth.repositories.UsuarioCursoRepository;
import br.com.ead.auth.services.UsuarioCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioCursoServiceImpl implements UsuarioCursoService {

    @Autowired
    UsuarioCursoRepository usuarioCursoRepository;

    @Override
    public boolean existsByUsuarioAndCurso(UserModel userModel, Long idCurso) {
        return this.usuarioCursoRepository.existsByUsuarioAndIdCurso(userModel, idCurso);
    }

    @Override
    public boolean existsByIdCurso(Long idCurso) {
        return this.usuarioCursoRepository.existsByIdCurso(idCurso);
    }

    @Override
    public UsuarioCursoModel save(UsuarioCursoModel usuarioCursoModel) {
        return this.usuarioCursoRepository.save(usuarioCursoModel);
    }

    @Override
    @Transactional
    public void deleteUsuarioCursoByCurso(Long idCurso) {
        this.usuarioCursoRepository.deleteAllByIdCurso(idCurso);
    }


}
