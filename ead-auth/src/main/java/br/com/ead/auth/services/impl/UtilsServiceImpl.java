package br.com.ead.auth.services.impl;

import br.com.ead.auth.services.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Override
    public String createUrlGetAllCursosByUsuario(Long idUsuario, Pageable pageable) {
        return  "/cursos?idUsuario=" + idUsuario + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }
}
