package br.com.ead.auth.clients;

import br.com.ead.auth.dtos.CursoDTO;
import br.com.ead.auth.dtos.ResponsePageDTO;
import br.com.ead.auth.services.UtilsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoClient {

    @Value("${ead.api.url.cursos}")
    private String REQUEST_URI_CURSOS;

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilsService utilsService;

    public Page<CursoDTO> getAllCursosByUsuario(Long idUsuario, Pageable pageable) {
        List<CursoDTO> cursos = new ArrayList<CursoDTO>();

        String url = this.REQUEST_URI_CURSOS + this.utilsService.createUrlGetAllCursosByUsuario(idUsuario, pageable);

        log.debug("Request URL: {} ", url);

        try {
            ParameterizedTypeReference<ResponsePageDTO<CursoDTO>> responseType =
                    new ParameterizedTypeReference<ResponsePageDTO<CursoDTO>>() {
                    };

            ResponseEntity<ResponsePageDTO<CursoDTO>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            cursos = result.getBody().getContent();

            log.debug("Response /cursos - Número de elementos: {} ", cursos.size());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /cursos {} ", e);
        }

        log.debug("Request finalizado /cursos idUsuario {} ", idUsuario);

        return new PageImpl<>(cursos);
    }

    public void deleteUsuarioInCurso(Long idUsuario) {
        String url = this.REQUEST_URI_CURSOS + "/cursos/usuarios/" + idUsuario;

        restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
    }
}
