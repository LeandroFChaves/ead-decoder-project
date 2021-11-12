package br.com.ead.curso.services.impl;

import br.com.ead.curso.repositories.CursoUsuarioRepository;
import br.com.ead.curso.services.CursoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

public class CursoUsuarioServiceImpl implements CursoUsuarioService {

    @Autowired
    CursoUsuarioRepository cursoUsuarioRepository;
}
