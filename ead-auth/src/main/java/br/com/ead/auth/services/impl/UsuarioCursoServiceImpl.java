package br.com.ead.auth.services.impl;

import br.com.ead.auth.repositories.UsuarioCursoRepository;
import br.com.ead.auth.services.UsuarioCursoService;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioCursoServiceImpl implements UsuarioCursoService {

    @Autowired
    UsuarioCursoRepository usuarioCursoRepository;
}
