package br.com.ead.curso.services.impl;

import br.com.ead.curso.models.UsuarioModel;
import br.com.ead.curso.repositories.UsuarioRepository;
import br.com.ead.curso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Page<UsuarioModel> findAll(Specification<UsuarioModel> spec, Pageable pageable) {
        return usuarioRepository.findAll(spec, pageable);
    }
}
