package br.com.ead.curso.services;

import org.springframework.data.domain.Pageable;

public interface UtilsService {
    String createUrlGetAllUsuariosByCurso(Long idCurso, Pageable pageable);
}
