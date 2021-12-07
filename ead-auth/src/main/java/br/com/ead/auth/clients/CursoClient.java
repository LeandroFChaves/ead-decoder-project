package br.com.ead.auth.clients;

import br.com.ead.auth.dtos.CursoDTO;
import br.com.ead.auth.dtos.ResponsePageDTO;
import br.com.ead.auth.services.UtilsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    //@Retry(name = "retryInstance", fallbackMethod = "retryFallback")
    //@CircuitBreaker(name = "circuitBreakerInstance", fallbackMethod = "circuitBreakerFallback")
    @CircuitBreaker(name = "circuitBreakerInstance")
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

    public Page<CursoDTO> circuitBreakerFallback(Long idUsuario, Pageable pageable, Throwable t) {
        log.error("Método de fallback circuit breaker para tratativa interna, causa - {}", t.toString());

        return new PageImpl<>(new ArrayList<>());
    }

    public Page<CursoDTO> retryFallback(Long idUsuario, Pageable pageable, Throwable t) {
        log.error("Método de fallback para tratativa interna, causa - {}", t.toString());

        return new PageImpl<>(new ArrayList<>());
    }

}
