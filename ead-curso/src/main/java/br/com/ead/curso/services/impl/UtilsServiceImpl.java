package br.com.ead.curso.services.impl;

import br.com.ead.curso.services.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Override
    public String createUrlGetAllUsuariosByCurso(Long idCurso, Pageable pageable) {
        return  "/usuarios?idCurso=" + idCurso + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }
}
