package br.com.ead.curso.services.impl;

import br.com.ead.curso.models.UsuarioModel;
import br.com.ead.curso.repositories.CursoRepository;
import br.com.ead.curso.repositories.UsuarioRepository;
import br.com.ead.curso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public Page<UsuarioModel> findAll(Specification<UsuarioModel> spec, Pageable pageable) {
        return this.usuarioRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<UsuarioModel> findById(Long usuarioDocente) {
        return this.usuarioRepository.findById(usuarioDocente);
    }

    @Override
    public UsuarioModel save(UsuarioModel userModel) {
        return this.usuarioRepository.save(userModel);
    }

    @Override
    @Transactional
    public void delete(Long idUsuario) {
        this.cursoRepository.deleteCursoUsuarioByUsuario(idUsuario);
        this.usuarioRepository.deleteById(idUsuario);
    }
}
