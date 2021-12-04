package br.com.ead.curso.services;

import br.com.ead.curso.models.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UsuarioService {

    Page<UsuarioModel> findAll(Specification<UsuarioModel> spec, Pageable pageable);
}
