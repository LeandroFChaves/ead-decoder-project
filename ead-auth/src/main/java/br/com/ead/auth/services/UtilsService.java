package br.com.ead.auth.services;

import org.springframework.data.domain.Pageable;

public interface UtilsService {
    String createUrlGetAllCursosByUsuario(Long idUsuario, Pageable pageable);
}
